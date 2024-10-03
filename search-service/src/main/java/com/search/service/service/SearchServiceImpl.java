package com.search.service.service;

import com.search.service.dto.SearchResultsDto;
import com.search.service.dto.SearchResultsListDto;
import com.search.service.entity.SearchResult;
import com.search.service.mapper.SearchResultsDtoToSearchResultMapper;
import com.search.service.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    //    private static final String URL = "http://google-service:8081/api/search?query=";
    private static final String URL = "http://localhost:8081/api/search?query=";

    private final RestTemplate restTemplate;
    private final SearchRepository searchRepository;
    private final SearchResultsDtoToSearchResultMapper mapper;

    @Override
    public List<SearchResultsDto> search(String query) {
        ResponseEntity<SearchResultsListDto> response = restTemplate.getForEntity(URL + query, SearchResultsListDto.class);
        List<SearchResult> searchResults = response.getBody().getSearchResultsList().stream()
                .map(mapper::map)
                .toList();
        searchRepository.saveAll(searchResults);
        return response.getBody().getSearchResultsList().stream()
                .map(item -> new SearchResultsDto(item.getTitle(), item.getLink()))
                .toList();
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
        }
        return null;
    }

    @Override
    public List<SearchResult> getAllResultsFromDatabase() {
        List<SearchResult> resultList = new ArrayList<>();
        searchRepository.findAll().forEach(resultList::add);
        return resultList;
    }

}
