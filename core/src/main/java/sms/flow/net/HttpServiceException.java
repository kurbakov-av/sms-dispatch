package sms.flow.net;

public class HttpServiceException extends RuntimeException {

    public HttpServiceException() {
    }

    public HttpServiceException(String message) {
        super(message);
    }

    public HttpServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpServiceException(Throwable cause) {
        super(cause);
    }

    public HttpServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
