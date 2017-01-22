package com.may.study.akka.ch08.node1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import com.may.study.akka.ch08.node2.PingService;

/**
 * 아카의 클러스터링을 보여주기 위한 메인 클래스
 */
public class Main {
    /**
     * 웹서버 역할을 하는 HttpActor와 라우터를 생성한다
     */
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ClusterSystem");
        ActorRef router = actorSystem.actorOf(Props.create(PingService.class).withRouter(new FromConfig()), "serviceRouter");
        ActorRef httpActor = actorSystem.actorOf(Props.create(HttpActor.class, router), "httpActor");
    }
}