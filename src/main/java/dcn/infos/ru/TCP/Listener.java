package dcn.infos.ru.TCP;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;

import java.util.Scanner;

/**
 * Created by Mark on 11.01.15.
 */
public class Listener extends UntypedActor {
    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("Адрес отправителя: " + getSender() + "\nMessage: " + msg.toString());
        return;
    }
}
