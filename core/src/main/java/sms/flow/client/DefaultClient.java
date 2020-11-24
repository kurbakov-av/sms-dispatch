package sms.flow.client;

import sms.flow.net.ResponseError;
import sms.flow.core.Sms;
import sms.flow.net.HttpService;
import sms.flow.net.Request;
import sms.flow.net.Response;

public class DefaultClient implements Client {
    protected final Provider provider;
    protected final HttpService httpService;

    public DefaultClient(Provider provider, HttpService httpService) {
        this.provider = provider;
        this.httpService = httpService;
    }

    @Override
    public Response send(Sms sms, Request request) {
        Request newRequest = request.toBuilder()
                .auth(provider.getAuthentication())
                .accept(provider.getAcceptType())
                .contentType(provider.getContentType())
                .build();

        Response response = httpService.execute(newRequest);
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new FailSendSmsException(new ResponseError(sms, response));
        }
    }

    public String getName() {
        return provider.getName();
    }
}
