package com.search.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResultsListDto {

    @JsonProperty(value = "items")
    public List<SearchResultsDto> searchResultsList;

    private SearchResultsListDto() {
        searchResultsList = new ArrayList<>();
    }
}

