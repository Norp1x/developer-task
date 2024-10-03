package com.search.service.service;

import com.search.service.dto.SearchResultsDto;
import com.search.service.dto.SearchResultsListDto;
import com.search.service.repository.SearchServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

//    private static final String URL = "http://google-service:8081/api/search?query=";
    private static final String URL = "http://localhost:8081/api/search?query=";

    RestTemplate restTemplate;
    SearchServiceRepository searchServiceRepository;

    @Override
    public List<SearchResultsDto> search(String query) {
        ResponseEntity<SearchResultsListDto> response = restTemplate.getForEntity(URL + query, SearchResultsListDto.class);
        return response.getBody().getSearchResultsList().stream()
                .map(item -> new SearchResultsDto(item.getTitle(), item.getLink()))
                .collect(Collectors.toList());

    }

    @Override
    public List<SearchResultsDto> saveInDatabase() {
        return List.of();
    }

}
