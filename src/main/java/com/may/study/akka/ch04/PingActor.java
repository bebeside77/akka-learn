package com.may.study.akka.ch04;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author bebeside77
 */
public class PingActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef child;

    public PingActor() {
        child = context().actorOf(Props.create(Ping1Actor.class), "ping1Actor");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            String msg = (String) message;
            if ("good".equals(msg) || "bad".equals(msg)) {
                child.tell(msg, getSelf());
            } else if ("done".equals(msg)) {
                log.info("all works are successfully completed.");
            }
        } else {
            unhandled(message);
        }
    }
}
