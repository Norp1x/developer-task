package com.search.service.controller;

import com.search.service.dto.SearchResultsDto;
import com.search.service.service.SearchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchServiceController {

    private SearchServiceImpl searchServiceImpl;

    @GetMapping
    public List<SearchResultsDto> search(@RequestParam String query) {
        return searchServiceImpl.search(query);
    }
}
