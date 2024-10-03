package com.search.service.mapper;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Date;

@Mapper(componentModel = "spring")
public interface SearchResultsDtoToSearchResultMapper {

    @Mapping(target = "date", expression = "java(currentDate())")
    SearchResult map(SearchResultsDto searchResultsDto);

    default Date currentDate() {
        return new Date(System.currentTimeMillis());
    }
}
