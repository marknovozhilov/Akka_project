package dcn.infos.ru.main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import dcn.infos.ru.actors.AppSystem;
import dcn.infos.ru.actors.HelloWorld;
import dcn.infos.ru.actors.Kernel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AppSystem appSystem = new AppSystem();
        final ActorRef kernel = appSystem.getSystem().actorOf(Props.create(Kernel.class), "kernel");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String f = sc.nextLine();
            if (f.equals("exit"))
                break;
            kernel.tell(f, ActorRef.noSender());

        }
        appSystem.getSystem().shutdown();


//        akka.Main.main(new String[]{HelloWorld.class.getName()});
    }
}
