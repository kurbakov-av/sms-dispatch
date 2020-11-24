package sms.flow.net;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Response {
    private final Map<String, Iterable<String>> headers;
    private final int statusCode;
    private final String body;
    private final Request request;

    private Response(Builder builder) {
        headers = builder.headers;
        statusCode = builder.statusCode;
        body = builder.body;
        request = builder.request;
    }

    public Builder toBuilder() {
        return new Builder()
                .headers(headers)
                .statusCode(statusCode)
                .body(body);
    }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 300;
    }

    public Map<String, Iterable<String>> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Request getRequest() {
        return request;
    }

    public static final class Builder {
        private Map<String, Iterable<String>> headers;
        private int statusCode;
        private String body;
        private Request request;

        public Builder header(String name, String... value) {
            if (name != null && !name.isEmpty()) {
                headers.put(name, List.of(value));
            }

            return this;
        }

        public Builder headers(Map<String, Iterable<String>> headers) {
            this.headers = headers;
            return this;
        }

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder request(Request request) {
            this.request = request;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
