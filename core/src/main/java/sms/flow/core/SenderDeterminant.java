package sms.flow.core;

public interface SenderDeterminant {
    SmsSender determinate(Sms sms);
}
