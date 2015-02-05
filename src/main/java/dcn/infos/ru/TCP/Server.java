package dcn.infos.ru.TCP;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.Tcp.Bound;
import akka.io.Tcp.CommandFailed;
import akka.io.Tcp.Connected;
import akka.io.TcpMessage;
import dcn.infos.ru.handlers.ServerHandler;

import java.net.InetSocketAddress;

public class Server extends UntypedActor {

    final ActorRef manager;

    public Server(ActorRef manager) {
        this.manager = manager;
    }

    @Override
    public void preStart() throws Exception {
        final ActorRef tcp = Tcp.get(getContext().system()).manager();
        tcp.tell(TcpMessage.bind(getSelf(),
                new InetSocketAddress("localhost", 30303), 100), getSelf());
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Bound) {
            manager.tell(msg, getSelf());
        } else if (msg instanceof CommandFailed) {
            getContext().stop(getSelf());

        } else if (msg instanceof Connected) {
            final Connected conn = (Connected) msg;
            manager.tell(conn, getSelf());

            final ActorRef handler = getContext().actorOf(
                    Props.create(ServerHandler.class));
            getSender().tell(TcpMessage.register(handler), getSelf());
        }
    }
}

