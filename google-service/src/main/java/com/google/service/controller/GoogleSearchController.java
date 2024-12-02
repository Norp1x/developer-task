package com.google.service.controller;

import com.google.service.dto.SearchResultsListDto;
import com.google.service.exception.ErrorResponse;
import com.google.service.exception.InputValidationException;
import com.google.service.service.GoogleSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;

/**
 * Created by Norpix on 03.11.2024.
 * Description: REST controller for handling search requests to an external Google API.
 * This controller provides an endpoint to send a search query and retrieve results from Google.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class GoogleSearchController {

    private GoogleSearchService googleSearchService;

    @Tag(
            name = "Google search")
    @Operation(
            summary = "Search request",
            description = "Request to external Google API with query parameter")
    @ApiResponse(
            responseCode = "200",
            description = "Successful response from Google",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = SearchResultsListDto.class))))
    @ApiResponse(
            responseCode = "503",
            description = "Cannot resolve connection",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    @ApiResponse(
            responseCode = "400",
            description = "Wrong input. Input can't be blank and must be between 2 and 50 characters",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = InputValidationException.class))))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResultsListDto getSearchResults(@RequestParam String query) throws ConnectException, InputValidationException {
        return googleSearchService.search(query);
    }

    @KafkaListener(topics = "search-topic", groupId = "google-service")
    @GetMapping(value = "/kafka", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResultsListDto query(@RequestParam String query) throws InputValidationException, ConnectException {
        return googleSearchService.search(query);
    }
}