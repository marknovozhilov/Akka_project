package dcn.infos.ru.actors;

import akka.actor.ActorSystem;

/**
 * Created by Mark on 10.01.15.
 */
public class AppSystem {
    private ActorSystem system;

    public AppSystem() {
        system = ActorSystem.create("actorSystem");
    }

    public ActorSystem getSystem() {
        return system;
    }

    public void setSystem(ActorSystem system) {
        this.system = system;
    }
}
