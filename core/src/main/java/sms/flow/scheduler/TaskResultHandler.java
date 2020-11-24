package sms.flow.scheduler;

import sms.flow.net.ResponseError;

public interface TaskResultHandler {

    void onSuccess(Object result);

    void onFail(ResponseError response, Throwable error);
}
