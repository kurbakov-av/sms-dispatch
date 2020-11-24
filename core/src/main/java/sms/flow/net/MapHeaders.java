package sms.flow.net;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MapHeaders {
    private final Map<String, Iterable<String>> source;
    private Map<String, String> headers;

    public MapHeaders(Map<String, Iterable<String>> source) {
        this.source = source;
    }

    public Map<String, String> getHeaders() {
        if (headers == null) {
            headers = new LinkedHashMap<>();
            for (Map.Entry<String, Iterable<String>> entry : source.entrySet()) {
                String name = entry.getKey();
                String value = StreamSupport.stream(entry.getValue().spliterator(), false)
                        .collect(Collectors.joining(";"));

                headers.put(name, value);
            }
        }

        return headers;
    }
}
