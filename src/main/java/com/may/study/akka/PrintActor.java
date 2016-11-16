package com.may.study.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author bebeside77
 */
public class PrintActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        Thread.sleep(1000);
        System.out.println(message);
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef printActor = system.actorOf(Props.create(PrintActor.class), "printActor");

        printActor.tell("hello akka", printActor);
    }
}
