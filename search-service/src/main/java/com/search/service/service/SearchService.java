package com.search.service.service;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResult;

import java.util.List;

public interface SearchService {
    List<SearchResultsDto> search(String query);

    SearchResult saveResultInDatabase(SearchResult searchResult);

    SearchResult getResultFromDatabase(Long id);

    List<SearchResult> getAllResultsFromDatabase();
}
