package sms.flow.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Provider {
    private final String name;
    private final String host;
    private final Authentication authentication;
    private final String acceptType;
    private final String contentType;
    private final Map<String, Object> defaultHeaders;

    private Provider(Builder builder) {
        name = builder.name;
        host = builder.host;
        authentication = builder.authentication;
        acceptType = builder.acceptType;
        contentType = builder.contentType;
        defaultHeaders = builder.defaultHeaders;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public String getAcceptType() {
        return acceptType;
    }

    public String getContentType() {
        return contentType;
    }

    public Map<String, Object> getDefaultHeaders() {
        return Collections.unmodifiableMap(defaultHeaders);
    }

    public static final class Builder {
        private final Map<String, Object> defaultHeaders = new LinkedHashMap<>();
        private String name;
        private String host;
        private Authentication authentication;
        private String acceptType;
        private String contentType;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder authentication(Authentication authentication) {
            this.authentication = authentication;
            return this;
        }

        public Builder acceptType(String acceptType) {
            this.acceptType = acceptType;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder defaultHeader(String name, String value) {
            this.defaultHeaders.put(name, value);
            return this;
        }

        public Builder defaultHeader(String name, Iterable<String> values) {
            values.forEach(v -> defaultHeaders.put(name, v));
            return this;
        }

        public Builder defaultHeaders(Map<String, Object> defaultHeaders) {
            this.defaultHeaders.clear();
            this.defaultHeaders.putAll(defaultHeaders);
            return this;
        }

        public Provider build() {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("No specified field [name]");
            }

            if (acceptType != null && !acceptType.isEmpty() && acceptType.toLowerCase().startsWith("accept")) {
                throw new IllegalArgumentException("Invalid header [Accept]. Set only header value");
            }

            if (contentType != null && !contentType.isEmpty() && contentType.toLowerCase().startsWith("content-type")) {
                throw new IllegalArgumentException("Invalid header [Content-Type]. Set only header value");
            }

            return new Provider(this);
        }
    }
}
