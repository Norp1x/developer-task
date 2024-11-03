package com.google.service.service;

import com.google.service.dto.GoogleSearchResponseListDto;
import com.google.service.exception.NoConnectionException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Implementation of the {@link GoogleSearchService} interface for performing search requests to the Google API.
 * This service constructs a query URL using API key and custom search engine ID (cx) parameters,
 * and sends a search request to Google Custom Search JSON API.
 */
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
    public GoogleSearchResponseListDto search(String query) {
        String URL = GOOGLE_SEARCH_URL + KEY_PARAM_STRING + apiKey + CX_PARAM_STRING + cx + QUERY_PARAM_STRING;

        try {
            return restTemplate.getForEntity(URL + query, GoogleSearchResponseListDto.class).getBody();
        } catch (NoConnectionException noConnectionException) {
            throw new NoConnectionException();
        }
    }
}
