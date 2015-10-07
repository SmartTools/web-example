package it.sevenbits.webExample;

import info.smart_tools.smartactors.core.FieldName;
import info.smart_tools.smartactors.core.IMessage;
import info.smart_tools.smartactors.core.actors.Actor;
import info.smart_tools.smartactors.core.actors.annotations.Handler;

public class NameConverter extends Actor {
    final FieldName name = new FieldName("name");
    final FieldName email = new FieldName("email");
    final FieldName data =  new FieldName("message");
    final FieldName error = new FieldName("error");

    @Handler("convert")
    public void convert(final IMessage message) {
        try {
            final String nameStr = (String) message.getValue(name);
            final String emailStr = (String) message.getValue(email);
            final String fullNameStr = String.format("%s <%s>", nameStr, emailStr);

            System.out.println(fullNameStr);
            respondOn(message, response -> response.setValue(data, "Success " + fullNameStr));
        } catch (Exception e) {
            //TODO: send exception to some error channel
            System.out.println(e.getMessage());
            respondOn(message, response -> {
                response.setValue(data, "Fail");
                response.setValue(error, e.getMessage());
            });
        }
    }
}
