package sms.flow.client;

import sms.flow.net.Request;

public interface Authentication {
    void authenticate(Request.Builder builder);
}
