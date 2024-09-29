package com.search.service.service;

import com.search.service.dto.SearchResultsDto;

import java.util.List;

public interface SearchService {
    List<SearchResultsDto> search(String query);
}
