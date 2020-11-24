package sms.flow.client;

public class KeyValueCredentials implements Credentials {
    private final String key;
    private final String value;

    public KeyValueCredentials(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getUsername() {
        return key;
    }

    @Override
    public String getPassword() {
        return value;
    }
}
