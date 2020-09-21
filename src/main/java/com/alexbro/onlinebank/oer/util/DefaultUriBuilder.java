package com.alexbro.onlinebank.oer.util;

import com.alexbro.onlinebank.oer.OerConstants;
import com.alexbro.onlinebank.oer.exception.OerException;
import com.alexbro.onlinebank.webfront.controller.pages.ExchangeStepTwoPageController;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class DefaultUriBuilder implements UriBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @Override
    public URI build(String url, String apiKey) {
        try {
            return new URIBuilder(url).addParameter("app_id", apiKey).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LOG.info(OerConstants.URI_BUILDING_FAILED_MESSAGE);
            throw new OerException(OerConstants.URI_BUILDING_FAILED_MESSAGE);
        }
    }
}
