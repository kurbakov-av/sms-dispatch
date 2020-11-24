package sms.flow.core;

import java.util.Objects;

public class Sms {
    private final String text;
    private final String phone;
    private final String senderName;

    public Sms(String text, String phone) {
        this(text, phone, "");
    }

    public Sms(String text, String phone, String senderName) {
        this.text = text;
        this.phone = phone;
        this.senderName = senderName;
    }

    public String getText() {
        return text;
    }

    public String getPhone() {
        return phone;
    }

    public String getSenderName() {
        return senderName;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "text='" + text + '\'' +
                ", phone='" + phone + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sms sms = (Sms) o;
        return Objects.equals(text, sms.text) &&
                Objects.equals(phone, sms.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, phone);
    }
}
