package com.search.service.mapper;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Date;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Mapper interface for converting {@link SearchResultsDto} objects to {@link SearchResult} entities.
 * This mapper uses MapStruct to automatically generate the implementation at compile time.
 * It also sets the current date on the mapped {@link SearchResult} entity.
 */
@Mapper(componentModel = "spring")
public interface SearchResultsDtoToSearchResultMapper {

    @Mapping(target = "date", expression = "java(currentDate())")
    SearchResult map(SearchResultsDto searchResultsDto);

    default Date currentDate() {
        return new Date(System.currentTimeMillis());
    }
}
