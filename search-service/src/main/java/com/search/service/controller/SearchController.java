package com.search.service.controller;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResult;
import com.search.service.exception.ErrorResponse;
import com.search.service.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping
public class SearchController {

    private final SearchService searchService;

    @Tag(
            name = "Search operations",
            description = "Available search requests")
    @Operation(
            summary = "Search endpoint",
            description = "Endpoint to make google search request with query parameter")
    @ApiResponse(
            responseCode = "200",
            description = "Successful response from Google",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SearchResultsDto.class))))
    @GetMapping("/search")
    public ResponseEntity<List<SearchResultsDto>> search(@RequestParam String query) {
        List<SearchResultsDto> responseBody = searchService.search(query);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Tag(
            name = "Database operations",
            description = "Available requests on the database")
    @Operation(
            summary = "Get request by Id",
            description = "Returns query stored in database with Link and Title")
    @ApiResponse(
            responseCode = "200",
            description = "Successful response from database",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SearchResult.class))))
    @ApiResponse(
            responseCode = "404",
            description = "Not found: \"The id '\" + id + \"' does not exist in database\"",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    @GetMapping("/database/{id}")
    public ResponseEntity<SearchResult> getSingleResultFromDatabase(@PathVariable long id) {
        SearchResult savedResult = searchService.getResultFromDatabase(id);
        return new ResponseEntity<>(savedResult, HttpStatus.OK);
    }

    @Tag(
            name = "Database operations")
    @Operation(
            summary = "Retrieves query responses",
            description = "Provides list of all queries with Link and Title")
    @ApiResponse(
            responseCode = "200",
            description = "Successful response from database",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SearchResult.class))))
    @GetMapping("/database/all")
    public ResponseEntity<List<SearchResult>> getAllResultsFromDatabase() {
        List<SearchResult> searchResults = searchService.getAllResultsFromDatabase();
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @Tag(
            name = "Database operations")
    @Operation(
            summary = "Save query response",
            description = "Saves single query to database")
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created in database",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SearchResult.class))))
    @PostMapping("/database")
    public ResponseEntity<SearchResult> saveInDatabase(@Valid @RequestBody SearchResult searchResult) {
        SearchResult saveResult = searchService.saveResultInDatabase(searchResult);
        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);
    }

    @Tag(
            name = "Database operations")
    @Operation(
            summary = "Delete query",
            description = "Deletes an entry from the database under a given id")
    @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted from database",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SearchResult.class))))
    @DeleteMapping("/database/delete/{id}")
    public ResponseEntity<HttpStatus> deleteByIdFromDatabase(@PathVariable Long id) {
        searchService.deleteByIdFromDatabase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
