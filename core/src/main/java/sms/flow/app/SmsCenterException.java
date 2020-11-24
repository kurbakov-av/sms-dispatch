package sms.flow.app;

public class SmsCenterException extends RuntimeException {

    public SmsCenterException() {
    }

    public SmsCenterException(String message) {
        super(message);
    }

    public SmsCenterException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsCenterException(Throwable cause) {
        super(cause);
    }

    public SmsCenterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
