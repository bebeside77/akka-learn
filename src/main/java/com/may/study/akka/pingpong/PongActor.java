package com.may.study.akka.pingpong;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 임의의 문자열 혹은 "ping" 메시지를 받으면 "pong" 메시지를 보내는 퐁액터
 * @author bebeside77
 */
public class PongActor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef ping;

	public PongActor(ActorRef ping) {
		this.ping = ping;
	}

	public void onReceive(Object message) throws Throwable {
		if (message instanceof String) {
			String msg = (String)message;
			log.info("Pong received {}", msg);
			ping.tell("pong", getSelf());
			Thread.sleep(1000); // 실전에서는 절대 금물!!!
		}
	}
}
