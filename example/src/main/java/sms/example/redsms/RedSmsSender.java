package sms.example.redsms;

import sms.flow.client.Client;
import sms.flow.core.Sms;
import sms.flow.core.SmsResponse;
import sms.flow.core.SmsSender;
import sms.flow.net.Request;
import sms.flow.net.Response;

public class RedSmsSender implements SmsSender {
    private final Client client;

    public RedSmsSender(Client client) {
        this.client = client;
    }

    @Override
    public SmsResponse send(Sms sms) {
        Request.Builder builder = new Request.Builder()
                .post("/api/message")
                .bodyParam("route", "sms")
                .bodyParam("text", sms.getText())
                .bodyParam("to", sms.getPhone());

        if (!sms.getSenderName().isEmpty()) {
            builder.bodyParam("from", sms.getSenderName());
        }

        Request request = builder.build();
        Response response = client.send(sms, request);

        return new SmsResponse(response.getBody());
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
