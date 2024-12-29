package com.search.service.mapper;

import com.search.service.dto.SearchResultsDto;
import com.search.service.entity.SearchResultMongoDb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Mapper interface for converting {@link SearchResultsDto} objects to {@link SearchResultMongoDb} entities.
 * This mapper uses MapStruct to automatically generate the implementation at compile time.
 * It also sets the current date on the mapped {@link SearchResultMongoDb} entity.
 */
@Mapper(componentModel = "spring")
public interface SearchResultsDtoToSearchResultMongoDbMapper {

    @Mapping(target = "dateTime", expression = "java(currentTime())")
    SearchResultMongoDb map(SearchResultsDto searchResultsDto);

    default Instant currentTime() {
        return Instant.now();
    }
}
