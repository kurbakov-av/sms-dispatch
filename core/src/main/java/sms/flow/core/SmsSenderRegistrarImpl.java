package sms.flow.core;

import sms.flow.client.Provider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SmsSenderRegistrarImpl implements SmsSenderRegistrar {

    private final Map<String, SmsSender> senderMap = new HashMap<>();

    public void addClient(Provider provider, SmsSender sender) {
        senderMap.put(provider.getName(), sender);
    }

    @Override
    public Map<String, SmsSender> getSenderMap() {
        return Collections.unmodifiableMap(senderMap);
    }
}
