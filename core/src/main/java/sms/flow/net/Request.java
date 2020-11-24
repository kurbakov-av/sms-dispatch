package sms.flow.net;

import sms.flow.client.Authentication;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Request {
    private final String uri;
    private final Map<String, Iterable<String>> headers;
    private final Map<String, String> queryParams;
    private final Map<String, String> bodyParams;
    private final Method method;
    private final String resource;

    private Request(Builder builder) {
        uri = builder.host;
        headers = builder.headers;
        queryParams = builder.queryParams;
        bodyParams = builder.bodyParams;
        method = builder.method;
        resource = builder.resource;
    }

    public Builder toBuilder() {
        return new Builder()
                .headers(headers)
                .queryParams(queryParams)
                .bodyParams(bodyParams)
                .host(uri)
                .method(method)
                .resource(resource);
    }

    public String getUri() {
        return uri;
    }

    public Map<String, Iterable<String>> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public Map<String, String> getQueryParams() {
        return Collections.unmodifiableMap(queryParams);
    }

    public Map<String, String> getBodyParams() {
        return Collections.unmodifiableMap(bodyParams);
    }

    public Method getMethod() {
        return method;
    }

    public String getResource() {
        return resource;
    }

    public static final class Builder {
        private final Map<String, Iterable<String>> headers = new LinkedHashMap<>();
        private final Map<String, String> queryParams = new LinkedHashMap<>();
        private final Map<String, String> bodyParams = new LinkedHashMap<>();
        private String host;
        private Method method = Method.GET;
        private String resource;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder resource(String resource) {
            this.resource = resource;
            return this;
        }

        public Builder accept(String... values) {
            return header("Accept", values);
        }

        public Builder contentType(String value) {
            return header("Content-Type", value);
        }

        public Builder header(String name, String... value) {
            if (name != null && !name.isEmpty()) {
                headers.put(name, List.of(value));
            }
            return this;
        }

        public Builder headers(Map<String, Iterable<String>> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder get(String resource) {
            this.method = Method.GET;
            this.resource = resource;
            return this;
        }

        public Builder post(String resource) {
            this.method = Method.POST;
            this.resource = resource;
            return this;
        }

        public Builder queryParam(String name, String value) {
            queryParams.put(name, value);
            return this;
        }

        public Builder queryParams(Map<String, String> params) {
            queryParams.putAll(params);
            return this;
        }

        public Builder bodyParam(String name, String value) {
            bodyParams.put(name, value);
            return this;
        }

        public Builder bodyParams(Map<String, String> params) {
            bodyParams.putAll(params);
            return this;
        }

        public Builder auth(Authentication authentication) {
            authentication.authenticate(this);
            return this;
        }

        public Request build() {
            if (!method.canContainsBody()) {
                headers.remove("Content-Type");
            }

            return new Request(this);
        }
    }
}
