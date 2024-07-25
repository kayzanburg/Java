package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.AbstractActor;
import com.typesafe.config.ConfigFactory;

public class Actor2 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor2System", ConfigFactory.load("application.conf"));
        system.actorOf(Props.create(Actor2Behavior.class), "actor2");
    }

    static class Actor2Behavior extends AbstractActor {
        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchAny(message -> {
                        System.out.println("Received message: " + message);
                        if (message.equals("Hi from Actor1")) {
                            String actor1Path = "akka://actor1System@127.0.0.1:2551/user/actor1";
                            ActorSelection actor1 = getContext().actorSelection(actor1Path);
                            actor1.tell("Hi from Actor2", getSelf());
                        }
                    })
                    .build();
        }
    }
}
