package sms.flow.scheduler;

import sms.flow.net.ResponseError;
import sms.flow.client.FailSendSmsException;
import sms.flow.app.SmsCenterException;
import sms.flow.core.SmsResponse;
import sms.flow.core.SenderDeterminant;
import sms.flow.core.SmsSender;
import sms.flow.net.HttpServiceException;

import java.util.Date;
import java.util.concurrent.*;

public class Java11DispatchScheduler implements DispatchScheduler {
    private final SenderDeterminant senderDeterminant;
    private final ScheduledExecutorService executorService;

    public Java11DispatchScheduler(SenderDeterminant senderDeterminant, ScheduledExecutorService executorService) {
        this.senderDeterminant = senderDeterminant;
        this.executorService = executorService;
    }

    @Override
    public void dispatch(SendSmsTask task, TaskResultHandler handler) {
        CompletableFuture.runAsync(() -> {
            Date now = new Date();
            long different = task.getStartAt().getTime() - now.getTime();
            long delay = TimeUnit.MILLISECONDS.toSeconds(different);

            SmsSender sender = senderDeterminant.determinate(task.getSms());
            ScheduledFuture<SmsResponse> future = executorService.schedule(
                    () -> sender.send(task.getSms()),
                    delay,
                    TimeUnit.SECONDS
            );

            try {
                SmsResponse response = future.get();
                handler.onSuccess(response);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (HttpServiceException e) {
                throw new SmsCenterException(e);
            } catch (ExecutionException | FailSendSmsException e) {
                ResponseError error = ((FailSendSmsException) e.getCause()).getErrorResponse();
                handler.onFail(error, e);
            }
        });
    }
}
