package sms.flow.net;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

public class UrlEncodedParams {
    private final Map<String, String> source;
    private String params = "";

    public UrlEncodedParams(Map<String, String> source) {
        this.source = source;
    }

    public String encodeUtf8() {
        return encode(StandardCharsets.UTF_8);
    }

    public String encode(Charset charset) {
        if ("".equals(params)) {
            StringBuilder queryStringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> queryParamsIterator = source.entrySet().iterator();
            if (queryParamsIterator.hasNext()) {
                queryStringBuilder.append("?");
                while (queryParamsIterator.hasNext()) {
                    Map.Entry<String, String> entry = queryParamsIterator.next();
                    queryStringBuilder
                            .append(URLEncoder.encode(entry.getKey(), charset))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), charset));

                    if (queryParamsIterator.hasNext()) {
                        queryStringBuilder.append("&");
                    }
                }

                params = queryStringBuilder.toString();
            }
        }

        return params;
    }

    public boolean isEmpty() {
        return params == null || params.equals("") || source.isEmpty();
    }
}
