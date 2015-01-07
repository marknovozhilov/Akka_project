package dcn.infos.ru.main;

import akka.actor.Actor;
import akka.actor.ActorKilledException;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Cancellable;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.Scheduler;
import akka.actor.Status;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.japi.Function;
import akka.pattern.Patterns;
import akka.util.Timeout;
import dcn.infos.ru.test.HelloWorld;

public class Main  {

    public static void main(String[] args) {
        akka.Main.main(new String[] { HelloWorld.class.getName() });
    }
}
