package com.may.study.akka.pingpong;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * 핑퐁액터 데모를 위한 메인 클래스
 * @author bebeside77
 */
public class Main {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
		ping.tell("start", ActorRef.noSender());
	}

}
