package sms.flow.client;

import sms.flow.core.Sms;
import sms.flow.net.Request;
import sms.flow.net.Response;

public interface Client {
    Response send(Sms sms, Request request);
}
