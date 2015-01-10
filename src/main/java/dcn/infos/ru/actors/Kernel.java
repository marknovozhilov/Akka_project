package dcn.infos.ru.actors;

import akka.actor.UntypedActor;

/**
 * Created by Mark on 10.01.15.
 */
public class Kernel extends UntypedActor {

    public static enum Messages {
        READY,
        MESSAGE,
        QUIT,
    }

    @Override
    public void onReceive(Object arg0) throws Exception {
        if (arg0 instanceof String) {
            String s = (String) arg0;
            System.out.println("Адрес отправителя: " + getSender() + "\nMessage: " + s);

            return;
        }
        unhandled(arg0);
    }
}