package sms.flow.client;

import sms.flow.net.ResponseError;

public class FailSendSmsException extends RuntimeException {
    private final ResponseError responseError;

    public FailSendSmsException(ResponseError responseError) {
        this.responseError = responseError;
    }

    public ResponseError getErrorResponse() {
        return responseError;
    }
}
