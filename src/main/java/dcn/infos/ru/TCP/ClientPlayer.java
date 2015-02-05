package dcn.infos.ru.TCP;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import dcn.infos.ru.actors.AppSystem;

import java.net.InetSocketAddress;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import dcn.infos.ru.actors.AppSystem;
import dcn.infos.ru.actors.Kernel;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created by Mark on 11.01.15.
 */
public class ClientPlayer {

    public static void main(String[] args) {
        AppSystem appSystem = new AppSystem();
        final ActorRef listener = appSystem.getSystem().actorOf(Props.create(Listener.class), "listener1");

        final ActorRef client2 = appSystem.getSystem().actorOf(Props.create(Client.class,
                new InetSocketAddress("localhost", 30303), listener), "client2");


    }
}
