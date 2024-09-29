package com.google.service.controller;

import com.google.service.dto.GoogleSearchResponseListDto;
import com.google.service.service.GoogleSearchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class GoogleSearchController {

    private GoogleSearchServiceImpl googleSearchServiceImpl;

    @GetMapping
    public GoogleSearchResponseListDto search(@RequestParam String query) {
        return googleSearchServiceImpl.search(query);
    }
}
