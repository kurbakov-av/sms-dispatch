package sms.flow.net;

public enum Method {
    GET,
    POST;

    public boolean canContainsBody() {
        return this == POST;
    }
}
