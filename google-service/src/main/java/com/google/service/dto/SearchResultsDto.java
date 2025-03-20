package com.google.service.dto;

import lombok.Data;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Data Transfer Object (DTO) representing a single item in the Google search response.
 * This class contains details about an individual search result, including the title and link.
 */
@Data
public class SearchResultsDto {

    /**
     * The title of the search result item.
     */
    private String title;
    /**
     * The link (URL) associated with the search result item.
     */
    private String link;
}
