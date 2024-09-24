package com.google.service.controller;

import com.google.service.dto.SearchResultsDto;
import com.google.service.service.GoogleSearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class GoogleSearchController {

    private GoogleSearchService googleSearchService;

    @GetMapping
    public List<SearchResultsDto> search(@RequestParam String query) {
        return googleSearchService.search(query);
    }
}
