package dcn.infos.ru.TCP;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Mark on 11.01.15.
 */
public class Manager extends UntypedActor {
    private HashSet<ActorPath> clients = new HashSet<ActorPath>();

    @Override
    public void onReceive(Object o) throws Exception {
        //String s = (String) arg0;
        System.out.println(this.getClass() + ": Адрес отправителя: " + getSender().path() + "\nMessage: " + o.toString());
        if (!clients.contains(getSender().path())) {
            clients.add(getSender().path());
        }

    }
}
