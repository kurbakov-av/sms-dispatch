package sms.flow.scheduler;

public interface DispatchScheduler {
    void dispatch(SendSmsTask task, TaskResultHandler handler);
}
