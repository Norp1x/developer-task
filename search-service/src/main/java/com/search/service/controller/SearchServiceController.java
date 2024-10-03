package com.search.service.controller;

import com.search.service.dto.SearchResultsDto;
import com.search.service.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchServiceController {

    SearchService searchService;

    @GetMapping
    public ResponseEntity<List<SearchResultsDto>> search(@RequestParam String query) {
        List<SearchResultsDto> responseBody = searchService.search(query);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping
    public List<SearchResultsDto> saveInDatabase() {
        return searchService.saveInDatabase();
    }
}
