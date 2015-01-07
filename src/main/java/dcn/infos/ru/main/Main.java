package dcn.infos.ru.main;

import dcn.infos.ru.test.HelloWorld;

public class Main  {

    public static void main(String[] args) {
        akka.Main.main(new String[] { HelloWorld.class.getName() });
    }
}
