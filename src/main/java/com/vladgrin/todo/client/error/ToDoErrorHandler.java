package com.vladgrin.todo.client.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

public class ToDoErrorHandler extends DefaultResponseErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ToDoErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        LOG.error(response.getStatusCode().toString());
        LOG.error(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
    }
}
