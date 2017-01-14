package com.may.study.akka.ch07;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author bebeside77
 */
public class CalculationActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Integer) {
            Integer msg = (Integer) message;
            log.info("CalculationActor received {}", msg);
            work(msg);
            getSender().tell(msg * 2, getSelf());
        }
    }

    private void work(Integer n) throws InterruptedException {
        log.info("CalculationActor working on " + n);
        Thread.sleep(1000);
        log.info("CalculationActor completed " + n);
    }
}
