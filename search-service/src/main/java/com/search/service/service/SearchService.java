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
    /**
     * Performs a search request based on the provided query.
     *
     * @param query the search query to be processed
     * @return a list of {@link SearchResultsDto} containing the search results
     * @throws InputValidationException if the input query is invalid
     */
    List<SearchResultsDto> search(String query) throws InputValidationException;

    /**
     * Saves a search result to the database.
     *
     * @param searchResult the {@link SearchResult} to be saved
     * @return the saved {@link SearchResult} entity
     */
    SearchResult saveResultInDatabase(SearchResult searchResult);

    /**
     * Retrieves a search result from the database based on the provided ID.
     *
     * @param id the ID of the search result to retrieve
     * @return the {@link SearchResult} with the specified ID
     */
    SearchResult getResultFromDatabase(Long id);

    /**
     * Retrieves the last 50 search results stored in the database, ordered by date in descending order.
     * This method is intended to limit the number of results returned to the most recent entries.
     *
     * @return a list of the 50 most recent {@link SearchResult} entries from the database
     */
    List<SearchResult> getLastFiftyResultsFromDatabase();

    /**
     * Deletes a search result from the database based on the provided ID.
     *
     * @param id the ID of the search result to delete
     */
    void deleteByIdFromDatabase(Long id);
}
