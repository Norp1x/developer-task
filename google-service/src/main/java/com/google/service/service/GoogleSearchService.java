package com.google.service.service;

import com.google.service.dto.SearchResultsListDto;
import com.google.service.exception.InputValidationException;

import java.net.ConnectException;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Service interface for handling search requests to an external Google API.
 * This interface defines a method to perform a search using a query parameter
 * and retrieve search results in the form of {@link SearchResultsListDto}.
 */
public interface GoogleSearchService {

    /**
     * Performs a search request to the Google API with the specified query.
     *
     * @param query the search query to send to the Google API
     * @return a {@link SearchResultsListDto} containing the search results from Google
     */
    SearchResultsListDto search(String query) throws ConnectException, InputValidationException;
}
