package sms.flow.core;

import java.util.Map;

public interface SmsSenderRegistrar {
    Map<String, SmsSender> getSenderMap();
}
