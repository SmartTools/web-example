package it.sevenbits.webExample;

import info.smart_tools.smartactors.core.MessageToBytesMapper;
import info.smart_tools.smartactors.core.actors.Actor;
import info.smart_tools.smartactors.core.actors.ActorSystem;
import info.smart_tools.smartactors.core.actors.ActorSystemBuilder;
import info.smart_tools.smartactors.core.addressing.targeting.actors.ActorPath;
import info.smart_tools.smartactors.core.services.endpoints.netty.http.HttpEndpoint;

import java.util.concurrent.ExecutionException;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ActorSystem actorSystem = new ActorSystemBuilder()
                .withThreads(4)
                .withEndpoint(receiver -> new HttpEndpoint(9000, receiver, new MessageToBytesMapper()))
                .build();

        actorSystem.start().get();

        Actor nameConverter = new NameConverter();
        ActorPath nameConverterPath = ActorPath.fromString("actor://nameConverter");
        actorSystem.connect(nameConverterPath, nameConverter);

        //actorSystem.stop().get();
    }
}
