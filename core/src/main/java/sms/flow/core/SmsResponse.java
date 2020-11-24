package sms.flow.core;

public class SmsResponse {
    private final String body;

    public SmsResponse(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "SmsResponse{" +
                "body='" + body + '\'' +
                '}';
    }
}
