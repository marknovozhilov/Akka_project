package dcn.infos.ru.TCP;

import akka.actor.UntypedActor;

/**
 * Created by Mark on 11.01.15.
 */
public class Listener extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        //String s = (String) arg0;
        System.out.println("Адрес отправителя: " + getSender() + "\nMessage: " + o.toString());

        return;
    }
}
