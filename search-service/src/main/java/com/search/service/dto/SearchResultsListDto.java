package com.search.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

