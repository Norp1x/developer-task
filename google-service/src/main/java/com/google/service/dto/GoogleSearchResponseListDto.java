package com.google.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoogleSearchResponseListDto {

    @JsonProperty(value = "items")
    private List<GoogleSearchResponseItemDto> responseItemsList;
}
