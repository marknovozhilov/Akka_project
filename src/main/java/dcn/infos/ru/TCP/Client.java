package dcn.infos.ru.TCP;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.japi.Procedure;
import akka.util.ByteString;
import dcn.infos.ru.handlers.ClientHandler;
import dcn.infos.ru.handlers.ServerHandler;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Client extends UntypedActor {

    final InetSocketAddress remote;
    final ActorRef listener;

    public Client(InetSocketAddress remote, ActorRef listener) {
        this.remote = remote;
        this.listener = listener;

        final ActorRef tcp = Tcp.get(getContext().system()).manager();
        tcp.tell(TcpMessage.connect(remote), getSelf());
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Tcp.CommandFailed) {
            listener.tell("failed", getSelf());
            getContext().stop(getSelf());

        } else if (msg instanceof Tcp.Connected) {
            final ActorRef handler = getContext().actorOf(
                    Props.create(ClientHandler.class));

            getSender().tell(TcpMessage.register(handler), getSelf());
            listener.tell(msg, getSelf());
//            getSender().tell(TcpMessage.register(getSelf()), getSelf());
//            getContext().become(connected(getSender()));

            Scanner sc = new Scanner(System.in);

            while (sc.hasNext()) {
                String f = sc.nextLine();
                if (f.equals("exit"))
                    break;
                getSender().tell(TcpMessage.write(ByteString.fromArray(f.getBytes())), getSelf());
            }
        } else if (msg instanceof Tcp.Received) {
            getSender().tell(TcpMessage.write(ByteString.fromArray(("Клиент получил сообщение")
                    .getBytes())), getSelf());
        }

    }

    private Procedure<Object> connected(final ActorRef connection) {
        return new Procedure<Object>() {
            @Override
            public void apply(Object msg) throws Exception {

                if (msg instanceof ByteString) {
                    connection.tell(TcpMessage.write((ByteString) msg), getSelf());


                } else if (msg instanceof Tcp.CommandFailed) {
                    // OS kernel socket buffer was full

                } else if (msg instanceof Tcp.Received) {
                    listener.tell(((Tcp.Received) msg).data(), getSelf());


                } else if (msg.equals("close")) {
                    connection.tell(TcpMessage.close(), getSelf());

                } else if (msg instanceof Tcp.ConnectionClosed) {
                    getContext().stop(getSelf());
                }
            }
        };
    }

}
