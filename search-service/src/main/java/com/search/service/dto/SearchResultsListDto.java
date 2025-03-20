package com.search.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Data Transfer Object (DTO) representing a list of search results.
 * This class is used to encapsulate a list of individual search results ({@link SearchResultsDto})
 * for easier data transfer within the application or to clients.
 */
@Data
@AllArgsConstructor
@Builder
public class SearchResultsListDto {

    @JsonProperty(value = "items")
    private List<SearchResultsDto> searchResultsList;

    public SearchResultsListDto() {
        searchResultsList = new ArrayList<>();
    }
}

