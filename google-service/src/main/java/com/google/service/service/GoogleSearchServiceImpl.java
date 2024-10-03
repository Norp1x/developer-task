package com.google.service.service;

import com.google.service.dto.GoogleSearchResponseListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return restTemplate.getForEntity(URL + query, GoogleSearchResponseListDto.class).getBody();
    }
}
