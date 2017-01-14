package com.may.study.akka.ch07;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.OnComplete;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author bebeside77
 */
public class NonblockingActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef child;
    private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
    private final ExecutionContext ec;

    public NonblockingActor() {
        child = context().actorOf(Props.create(CalculationActor.class), "calculationActor");
        ec = context().system().dispatcher();
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Integer) {
            Future<Object> future = Patterns.ask(child, message, timeout);

            future.onSuccess(new SaySuccess<>(), ec);
            future.onComplete(new SayComplete<>(), ec);
            future.onFailure(new SayFailure(), ec);
        } else if (message instanceof String) {
            log.info("BlockingActor received a message: " + message);
        }
    }

    private class SaySuccess<T> extends OnSuccess<T> {
        @Override
        public void onSuccess(T result) throws Throwable {
            log.info("Succeeded with " + result);
        }
    }

    private class SayComplete<T> extends OnComplete<T> {
        @Override
        public void onComplete(Throwable throwable, T result) throws Throwable {
            log.info("Completed.");
        }
    }

    private class SayFailure extends OnFailure {
        @Override
        public void onFailure(Throwable t) throws Throwable {
            log.info("Failed with " + t);
        }
    }
}
