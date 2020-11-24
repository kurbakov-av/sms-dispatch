package sms.flow.core;

public interface ServiceRegistrarAdapter {

    default void configure(SmsSenderRegistrarImpl registrar) {

    }
}
