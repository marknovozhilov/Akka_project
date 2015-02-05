package dcn.infos.ru.handlers;

import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;

public class ServerHandler extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Tcp.Received) {
            final ByteString data = ((Tcp.Received) msg).data();
            System.out.println("Получил (" + this.getClass() + "): " + data.utf8String());

            getSender().tell(TcpMessage.write(ByteString.fromArray("Hello".getBytes())), getSelf());

        } else if (msg instanceof Tcp.ConnectionClosed) {
            getContext().stop(getSelf());
        }
    }
}