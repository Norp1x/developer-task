package com.search.service.service;

import com.search.service.dto.SearchResultsDto;
import com.search.service.dto.SearchResultsListDto;
import com.search.service.entity.SearchResult;
import com.search.service.exception.InputValidationException;
import com.search.service.exception.NoConnectionException;
import com.search.service.exception.NoSearchResultException;
import com.search.service.mapper.SearchResultsDtoToSearchResultMapper;
import com.search.service.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.sql.Date;
import java.util.ArrayList;
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

    //    private static final String URL = "http://google-service:8081/api/search?query=";
    private static final String URL = "http://localhost:8081/api/search?query=";

    private final RestTemplate restTemplate;
    private final SearchRepository searchRepository;
    private final SearchResultsDtoToSearchResultMapper mapper;

    @Override
    public List<SearchResultsDto> search(String query) throws InputValidationException {
        if (query.isBlank() || query.length() < 2 || query.length() > 50) {
            throw new InputValidationException();
        }
        try {
            ResponseEntity<SearchResultsListDto> response = restTemplate.getForEntity(URL + query, SearchResultsListDto.class);
            List<SearchResult> searchResults = response.getBody().getSearchResultsList().stream()
                    .map(mapper::map)
                    .toList();
            searchRepository.saveAll(searchResults);
            return response.getBody().getSearchResultsList().stream()
                    .map(item -> new SearchResultsDto(item.getTitle(), item.getLink()))
                    .toList();
        } catch (RestClientException restClientException) {
            if (restClientException.getCause() instanceof ConnectException) {
                throw new NoConnectionException();
            }
            throw restClientException;
        }
    }

    @Override
    public SearchResult saveResultInDatabase(SearchResult searchResult) {
        Date date = new Date(System.currentTimeMillis());
        searchResult.setDate(date);
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
    public List<SearchResult> getAllResultsFromDatabase() {
        List<SearchResult> resultList = new ArrayList<>();
        searchRepository.findAll().forEach(resultList::add);
        return resultList;
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
