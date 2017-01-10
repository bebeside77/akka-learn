package com.may.study.akka.ch05;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 *
 * @author bebeside77
 */
public class Main {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
		ping.tell("work", ActorRef.noSender());
		ping.tell("reset", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender()); // this message is ignored
	}
}
