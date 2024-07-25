package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.AbstractActor;
import com.typesafe.config.ConfigFactory;

public class Actor1 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor1System", ConfigFactory.load("application.conf"));
        ActorRef actor1 = system.actorOf(Props.create(Actor1Behavior.class), "actor1");
        actor1.tell("start", ActorRef.noSender());
    }

    static class Actor1Behavior extends AbstractActor {
        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("start", message -> {
                        String actor2Path = "akka://actor2System@127.0.0.1:2552/user/actor2";
                        ActorSelection actor2 = getContext().actorSelection(actor2Path);
                        actor2.tell("Hi from Actor1", getSelf());
                    })
                    .matchAny(message -> {
                        System.out.println("Received message: " + message);
                    })
                    .build();
        }
    }
}
