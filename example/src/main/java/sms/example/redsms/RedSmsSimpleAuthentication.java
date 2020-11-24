package sms.example.redsms;

import sms.flow.client.Authentication;
import sms.flow.client.Credentials;
import sms.flow.net.Request;

import java.util.Base64;
import java.util.UUID;

public class RedSmsSimpleAuthentication implements Authentication {

    private final Credentials credentials;

    public RedSmsSimpleAuthentication(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public void authenticate(Request.Builder builder) {
        String ts = UUID.randomUUID().toString();
        String secret = Base64.getEncoder().encodeToString((ts + credentials.getPassword()).getBytes());

        builder.header("login", credentials.getUsername());
        builder.header("ts", ts);
        builder.header("secret", secret);
    }
}
