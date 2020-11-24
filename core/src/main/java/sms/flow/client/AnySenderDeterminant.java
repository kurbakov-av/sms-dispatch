package sms.flow.client;

import sms.flow.core.Sms;
import sms.flow.core.SmsSender;
import sms.flow.core.SenderDeterminant;
import sms.flow.core.SmsSenderRegistrar;

import java.util.Map;

public class AnySenderDeterminant implements SenderDeterminant {
    private final SmsSenderRegistrar registrar;

    public AnySenderDeterminant(SmsSenderRegistrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public SmsSender determinate(Sms sms) {
        Map<String, SmsSender> senderMap = registrar.getSenderMap();
        for (Map.Entry<String, SmsSender> entry : senderMap.entrySet()) {
            SmsSender sender = entry.getValue();
            if (sender.isAvailable()) {
                return sender;
            }
        }

        throw new RuntimeException("Senders is not available");
    }
}
