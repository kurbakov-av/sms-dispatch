package sms.flow.net;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Java11HttpService implements HttpService {

    private final HttpClient httpClient;

    public Java11HttpService() {
        this(HttpClient.newHttpClient());
    }

    public Java11HttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Response execute(Request request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();

        MapHeaders mapHeaders = new MapHeaders(request.getHeaders());
        mapHeaders.getHeaders().forEach(builder::header);

        HttpRequest.BodyPublisher bodyPublishers = HttpRequest.BodyPublishers.noBody();
        if (request.getMethod().canContainsBody()) {
            UrlEncodedParams bodyParams = new UrlEncodedParams(request.getBodyParams());
            if (!bodyParams.isEmpty()) {
                bodyPublishers = HttpRequest.BodyPublishers.ofString(bodyParams.encodeUtf8());
            }
        }

        builder.method(request.getMethod().name(), bodyPublishers);

        UrlEncodedParams queryParamsString = new UrlEncodedParams(request.getQueryParams());
        String requestUri = request.getUri().concat(request.getResource());
        URI targetUri = URI.create(requestUri).resolve(queryParamsString.encodeUtf8());
        HttpRequest httpRequest = builder.uri(targetUri).build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> responseHeaders = httpResponse.headers().map();
            Map<String, Iterable<String>> iterableMap = new LinkedHashMap<>();
            responseHeaders.forEach(iterableMap::put);

            return new Response.Builder()
                    .request(request)
                    .statusCode(httpResponse.statusCode())
                    .headers(iterableMap)
                    .body(httpResponse.body())
                    .build();

        } catch (IOException | InterruptedException e) {
            throw new HttpServiceException(e);
        }
    }
}
