package sms.flow.app;

import sms.flow.core.Sms;
import sms.flow.scheduler.DispatchScheduler;
import sms.flow.scheduler.SendSmsTask;
import sms.flow.scheduler.TaskResultHandler;

import java.util.Date;

public class SmsCenter {
    private final DispatchScheduler dispatchScheduler;

    public SmsCenter(DispatchScheduler dispatchScheduler) {
        this.dispatchScheduler = dispatchScheduler;
    }

    public void send(Sms sms, Date startAt, TaskResultHandler handler) {
        SendSmsTask task = new SendSmsTask(sms, startAt);
        dispatchScheduler.dispatch(task, handler);
    }
}
