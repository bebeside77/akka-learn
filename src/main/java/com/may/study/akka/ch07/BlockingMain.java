package com.may.study.akka.ch07;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author bebeside77
 */
public class BlockingMain {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef blockingActor = actorSystem.actorOf(Props.create(BlockingActor.class), "blockingActor");
        blockingActor.tell(10, ActorRef.noSender());
        blockingActor.tell("hello", ActorRef.noSender());
    }
}
