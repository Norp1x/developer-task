package com.google.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Data Transfer Object (DTO) representing a list of search results from the Google API response.
 * This class encapsulates multiple search result items, each represented by {@link GoogleSearchResponseItemDto}.
 */
@Data
public class GoogleSearchResponseListDto {

    @JsonProperty(value = "items")
    private List<GoogleSearchResponseItemDto> responseItemsList;
}
