package com.markit.kyc.citrus;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomResponseErrorHandler.class.getName());

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    public void handleError(ClientHttpResponse response) throws IOException {

        List<String> customHeader = response.getHeaders().get("x-app-err-id");

        String svcErrorMessageID = "";
        if (customHeader != null) {
            svcErrorMessageID = customHeader.get(0);                
        }

        try {           

            errorHandler.handleError(response);

        } catch (RestClientException scx) {         

        	logger.error("CustomExeption {} {}", scx.getMessage(), svcErrorMessageID, scx);
        }
    }

    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }
}