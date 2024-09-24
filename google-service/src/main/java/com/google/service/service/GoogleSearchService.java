package com.google.service.service;

import com.google.service.dto.GoogleSearchResponseListDto;
import com.google.service.dto.SearchResultsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoogleSearchService {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.cx}")
    private String cx;

    private static final String GOOGLE_SEARCH_URL = "https://www.googleapis.com/customsearch/v1";

    public List<SearchResultsDto> search(String query) {
        String url = GOOGLE_SEARCH_URL + "?key=" + apiKey + "&cx=" + cx + "&q=" + query;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleSearchResponseListDto> response = restTemplate.getForEntity(url, GoogleSearchResponseListDto.class);

        return response.getBody().getResponseItemsList().stream()
                .map(item -> new SearchResultsDto(item.getTitle(), item.getLink()))
                .collect(Collectors.toList());
    }
}
