package com.google.service.controller;

import com.google.service.dto.GoogleSearchResponseListDto;
import com.google.service.service.GoogleSearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class GoogleSearchController {

    GoogleSearchService googleSearchService;

    @GetMapping
    public GoogleSearchResponseListDto getSearchResults(@RequestParam String query) {
        return googleSearchService.search(query);
    }
}
