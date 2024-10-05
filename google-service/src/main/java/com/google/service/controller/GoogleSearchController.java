package com.google.service.controller;

import com.google.service.dto.GoogleSearchResponseListDto;
import com.google.service.exception.ErrorResponse;
import com.google.service.service.GoogleSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class GoogleSearchController {

    GoogleSearchService googleSearchService;

    @Tag(
            name = "Google search")
    @Operation(
            summary = "Search request",
            description = "Request to external Google API with query parameter")
    @ApiResponse(
            responseCode = "200",
            description = "Successful response from Google",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = GoogleSearchResponseListDto.class))))
    @ApiResponse(
            responseCode = "503",
            description = "Cannot resolve connection",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public GoogleSearchResponseListDto getSearchResults(@RequestParam String query) {
        return googleSearchService.search(query);
    }
}
