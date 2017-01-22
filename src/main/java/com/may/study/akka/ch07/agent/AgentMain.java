package com.may.study.akka.ch07.agent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author bebeside77
 */
public class AgentMain {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef actorRef = actorSystem.actorOf(Props.create(AgentActor.class), "agentActor");
        actorRef.tell("", ActorRef.noSender());
    }
}
