package com.search.service.controller;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResult;
import com.search.service.service.SearchService;
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

    @GetMapping("/search")
    public ResponseEntity<List<SearchResultsDto>> search(@RequestParam String query) {
        List<SearchResultsDto> responseBody = searchService.search(query);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/database/{id}")
    public ResponseEntity<SearchResult> getSingleResultFromDatabase(@PathVariable long id) {
        SearchResult savedResult = searchService.getResultFromDatabase(id);
        return new ResponseEntity<>(savedResult, HttpStatus.OK);
    }

    @GetMapping("/database/all")
    public ResponseEntity<List<SearchResult>> getAllResultsFromDatabase() {
        List<SearchResult> searchResults = searchService.getAllResultsFromDatabase();
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PostMapping("/database")
    public ResponseEntity<SearchResult> saveInDatabase(@RequestBody SearchResult searchResult) {
        SearchResult saveResult = searchService.saveResultInDatabase(searchResult);
        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);
    }
}
