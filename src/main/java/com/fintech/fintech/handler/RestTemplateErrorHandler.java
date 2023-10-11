package com.fintech.fintech.handler;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

@Slf4j
@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode()
                .is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("rest client error occurred -> code: '{}'; message: '{}'",
                  response.getStatusCode(),
                  IOUtils.toString(response.getBody(), StandardCharsets.UTF_8));

        if (response.getStatusCode().is5xxServerError()) {
            throw new HttpServerErrorException(response.getStatusCode(), response.getStatusText(),
                                               response.getBody().readAllBytes(),
                                               StandardCharsets.UTF_8);
        } else if (response.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(response.getStatusCode(), response.getStatusText(),
                                               response.getBody().readAllBytes(),
                                               StandardCharsets.UTF_8);
        }
    }
}
