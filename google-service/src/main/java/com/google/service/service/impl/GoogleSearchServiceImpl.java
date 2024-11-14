package com.google.service.service.impl;

import com.google.service.dto.SearchResultsListDto;
import com.google.service.exception.InputValidationException;
import com.google.service.exception.NoConnectionException;
import com.google.service.service.GoogleSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Implementation of the {@link GoogleSearchService} interface for performing search requests to the Google API.
 * This service constructs a query URL using API key and custom search engine ID (cx) parameters,
 * and sends a search request to Google Custom Search JSON API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleSearchServiceImpl implements GoogleSearchService {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.cx}")
    private String cx;

    private static final String KEY_PARAM_STRING = "?key=";
    private static final String CX_PARAM_STRING = "&cx=";
    private static final String QUERY_PARAM_STRING = "&q=";
    private static final String GOOGLE_SEARCH_URL = "https://www.googleapis.com/customsearch/v1";

    final RestTemplate restTemplate;

    @Override
    public SearchResultsListDto search(String query) throws InputValidationException {
        if (query.isBlank() || query.length() < 2 || query.length() > 50) {
            throw new InputValidationException();
        }
        String URL = GOOGLE_SEARCH_URL + KEY_PARAM_STRING + apiKey + CX_PARAM_STRING + cx + QUERY_PARAM_STRING;
        try {
            return restTemplate.getForEntity(URL + query, SearchResultsListDto.class).getBody();
        } catch (RestClientException restClientException) {
            log.error("Connection exception ", restClientException);
            throw new NoConnectionException(restClientException);
        }
    }
}
