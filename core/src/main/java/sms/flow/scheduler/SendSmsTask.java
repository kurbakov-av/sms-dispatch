package sms.flow.scheduler;

import sms.flow.core.Sms;

import java.util.Date;

public class SendSmsTask {
    private final Sms sms;
    private final Date startAt;

    public SendSmsTask(Sms sms, Date startAt) {
        this.sms = sms;
        this.startAt = startAt;
    }

    public Sms getSms() {
        return sms;
    }

    public Date getStartAt() {
        return startAt;
    }
}
