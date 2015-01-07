//
//import akka.actor.Actor;
//import akka.actor.ActorKilledException;
//import akka.actor.ActorRef;
//import akka.actor.ActorRefFactory;
//import akka.actor.Cancellable;
//import akka.actor.OneForOneStrategy;
//import akka.actor.Props;
//import akka.actor.Scheduler;
//import akka.actor.Status;
//import akka.actor.SupervisorStrategy;
//import akka.actor.SupervisorStrategy.Directive;
//import akka.actor.Terminated;
//import akka.actor.UntypedActor;
//import akka.japi.Function;
//import akka.pattern.Patterns;
//import akka.util.Timeout;

package dcn.infos.ru.test;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else
            unhandled(msg);
    }

}
