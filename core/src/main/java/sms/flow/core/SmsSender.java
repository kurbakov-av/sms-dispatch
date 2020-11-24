package sms.flow.core;

public interface SmsSender {
    SmsResponse send(Sms sms);

    boolean isAvailable();
}
