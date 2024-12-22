package com.search.service.service.impl;

import com.search.service.config.SearchServiceConfig;
import com.search.service.dto.SearchResultsDto;
import com.search.service.dto.SearchResultsListDto;
import com.search.service.entity.SearchResult;
import com.search.service.exception.InputValidationException;
import com.search.service.exception.NoConnectionException;
import com.search.service.exception.NoSearchResultException;
import com.search.service.mapper.SearchResultsDtoToSearchResultMapper;
import com.search.service.repository.SearchRepository;
import com.search.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Implementation of the {@link SearchService} interface, providing methods to perform search operations
 * and interact with the database. This service connects to an external Google-Service API to retrieve search results,
 * validates inputs, and manages database records.
 */
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchServiceConfig searchServiceConfig;
    private final RestTemplate restTemplate;
    private final SearchRepository searchRepository;
    @Qualifier("searchResultsDtoToSearchResultMapperImpl")
    private final SearchResultsDtoToSearchResultMapper mapper;

    @Override
    public List<SearchResultsDto> search(String query) throws InputValidationException {
        return getSearchResults(query);
    }

    @Override
    public List<SearchResultsDto> kafkaSearch(String query) throws InputValidationException {
        return getSearchResults(query);
    }

    private List<SearchResultsDto> getSearchResults(String query) throws InputValidationException {
        if (query.isBlank() || query.length() < 2 || query.length() > 50) {
            throw new InputValidationException();
        }
        try {
            ResponseEntity<SearchResultsListDto> response = restTemplate.getForEntity(searchServiceConfig.getBaseUrl()
                    + searchServiceConfig.getApiPath() + query, SearchResultsListDto.class);

            List<SearchResult> searchResults = response.getBody().getSearchResultsList().stream()
                    .map(mapper::map)
                    .toList();
            searchRepository.saveAll(searchResults);
            return response.getBody().getSearchResultsList().stream()
                    .map(item -> new SearchResultsDto(item.getTitle(), item.getLink()))
                    .toList();
        } catch (RestClientException restClientException) {
            throw new NoConnectionException(restClientException);
        }
    }

    @Override
    public SearchResult saveResultInDatabase(SearchResult searchResult) {
        searchResult.setDateTime(mapper.currentTime());
        return searchRepository.save(searchResult);
    }

    @Override
    public SearchResult getResultFromDatabase(Long id) {
        if (searchRepository.findById(id).isPresent()) {
            return searchRepository.findById(id).get();
        } else {
            throw new NoSearchResultException(id);
        }
    }

    @Override
    public List<SearchResult> getLastFiftyResultsFromDatabase() {
        return searchRepository.findTop50ByOrderByIdDesc();
    }

    @Override
    public void deleteByIdFromDatabase(Long id) {
        if (searchRepository.findById(id).isPresent()) {
            searchRepository.deleteById(id);
        } else {
            throw new NoSearchResultException(id);
        }
    }

}
