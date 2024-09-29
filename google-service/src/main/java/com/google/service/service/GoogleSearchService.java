package com.google.service.service;

import com.google.service.dto.GoogleSearchResponseListDto;

public interface GoogleSearchService {
    GoogleSearchResponseListDto search(String query);
}
