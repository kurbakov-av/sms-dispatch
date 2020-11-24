package sms.flow.net;

import sms.flow.core.Sms;

public class ResponseError {
    private final Sms sms;
    private final Response response;

    public ResponseError(Sms sms, Response response) {
        this.sms = sms;
        this.response = response;
    }

    public Sms getSms() {
        return sms;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "sms=" + sms +
                ", response=" + response +
                '}';
    }
}
