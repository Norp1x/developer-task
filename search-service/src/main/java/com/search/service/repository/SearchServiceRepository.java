package com.search.service.repository;

import com.search.service.dto.SearchResultsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Repository
public class SearchServiceRepository {

    private List<SearchResultsDto> requestData = new ArrayList<>();

    public SearchResultsDto getSearchResults(int index) {
        return requestData.get(index);
    }

    public void addData(SearchResultsDto data) {
        requestData.add(data);
    }
}
