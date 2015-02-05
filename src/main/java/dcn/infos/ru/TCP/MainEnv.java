package dcn.infos.ru.TCP;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import dcn.infos.ru.actors.AppSystem;
import dcn.infos.ru.actors.Kernel;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created by Mark on 11.01.15.
 */
public class MainEnv {

    public static void main(String[] args) {
        AppSystem appSystem = new AppSystem();
        final ActorRef listener = appSystem.getSystem().actorOf(Props.create(Listener.class), "listener");
        final ActorRef manager = appSystem.getSystem().actorOf(Props.create(Manager.class), "manager");

        final ActorRef server = appSystem.getSystem().actorOf(Props.create(Server.class, manager), "server");
        final ActorRef client1 = appSystem.getSystem().actorOf(Props.create(Client.class,
                new InetSocketAddress("localhost", 30303), listener), "client1");

    }
}
