package com.search.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Data Transfer Object (DTO) representing a search result.
 * This class is used to transfer search result data, including the title and link,
 * between different parts of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultsDto {

    private String title;
    private String link;
}
