package dcn.infos.ru.TCP;

import akka.actor.ActorRef;
import akka.actor.Props;
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

        final ActorRef server = appSystem.getSystem().actorOf(Props.create(Server.class, listener), "server");
        final ActorRef client = appSystem.getSystem().actorOf(Props.create(Client.class,
                new InetSocketAddress("localhost", 30303), manager), "client");

//        Server server = new Server(manager);
//        Client client = new Client(new InetSocketAddress("localhost",30303), listener);


//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String f = sc.nextLine();
//            if (f.equals("exit"))
//                break;
//            kernel.tell(f, ActorRef.noSender());
//
//        }
//        appSystem.getSystem().shutdown();

//        akka.Main.main(new String[]{HelloWorld.class.getName()});
    }
}
