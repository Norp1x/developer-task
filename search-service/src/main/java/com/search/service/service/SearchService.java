package com.search.service.service;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResult;
import com.search.service.exception.InputValidationException;

import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Service interface for managing search operations and interactions with the database.
 * This service provides methods to perform search requests, save results, retrieve
 * individual or all results from the database, and delete results by ID.
 */
public interface SearchService {
    List<SearchResultsDto> search(String query) throws InputValidationException;

    SearchResult saveResultInDatabase(SearchResult searchResult);

    SearchResult getResultFromDatabase(Long id);

    List<SearchResult> getLastFiftyResultsFromDatabase();

    void deleteByIdFromDatabase(Long id);
}
