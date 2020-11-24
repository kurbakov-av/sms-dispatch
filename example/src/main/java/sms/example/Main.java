package sms.example;

import sms.example.redsms.RedSmsSender;
import sms.example.redsms.RedSmsSimpleAuthentication;
import sms.flow.app.SmsCenter;
import sms.flow.client.*;
import sms.flow.core.*;
import sms.flow.net.Java11HttpService;
import sms.flow.net.ResponseError;
import sms.flow.scheduler.Java11DispatchScheduler;
import sms.flow.scheduler.TaskResultHandler;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Provider provider = new Provider.Builder()
                .name("redsms")
                .authentication(new RedSmsSimpleAuthentication(new KeyValueCredentials("login", "pass")))
                .acceptType("application/json")
                .contentType("application/x-www-form-urlencoded")
                .build();

        Client client = new DefaultClient(provider, new Java11HttpService());
        ServiceRegistrarAdapter adapter = new ServiceRegistrarAdapter() {
            @Override
            public void configure(SmsSenderRegistrarImpl registrar) {
                registrar.addClient(provider, new RedSmsSender(client));
            }
        };

        ServiceRegistrarConfigure serviceRegistrarConfigure = new ServiceRegistrarConfigure(adapter);
        SmsSenderRegistrar smsSenderRegistrar = serviceRegistrarConfigure.configure();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        SenderDeterminant anySender = new AnySenderDeterminant(smsSenderRegistrar);
        SmsCenter smsCenter = new SmsCenter(new Java11DispatchScheduler(anySender, executorService));

        TaskResultHandler handler = new TaskResultHandler() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("result = " + result);
            }

            @Override
            public void onFail(ResponseError response, Throwable error) {
                System.out.println("response = " + response);
                System.out.println("error.getMessage() = " + error.getMessage());
            }
        };

        smsCenter.send(new Sms("text", "phone"), new Date(new Date().getTime() + Duration.ofSeconds(1L).toMillis()), handler);
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}
