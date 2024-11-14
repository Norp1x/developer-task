package com.search.service.service

import com.search.service.config.SearchServiceConfig
import com.search.service.dto.SearchResultsDto
import com.search.service.dto.SearchResultsListDto
import com.search.service.entity.SearchResult
import com.search.service.mapper.SearchResultsDtoToSearchResultMapper
import com.search.service.repository.SearchRepository
import com.search.service.service.impl.SearchServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class SearchServiceImplTest extends Specification {

    def restTemplate = Mock(RestTemplate)
    def searchRepository = Mock(SearchRepository)
    def mapper = Mock(SearchResultsDtoToSearchResultMapper)
    def config = Mock(SearchServiceConfig)
    def searchService = new SearchServiceImpl(config, restTemplate, searchRepository, mapper)

    def "Should return list of results when search query requested"() {
        given:
        def query = "test"
        List<SearchResultsDto> searchResultsDto = SearchResultsDto.builder()
                .title("testTitle")
                .link("testLink")
                .build() as List<SearchResultsDto>
        def searchResultsListDto = new SearchResultsListDto(searchResultsDto)
        def responseEntity = ResponseEntity.ok(searchResultsListDto)

        when:
        restTemplate.getForEntity(_ as String, SearchResultsListDto.class) >> responseEntity
        mapper.map(_ as SearchResultsDto) >> new SearchResult()
        def result = searchService.search(query)

        then:
        result.size() == 1
        result[0].title == "testTitle"
        result[0].link == "testLink"
        1 * searchRepository.saveAll(_)
    }

    def "SaveResultInDatabase"() {
    }

    def "GetResultFromDatabase"() {
    }

    def "GetAllResultsFromDatabase"() {
    }

    def "DeleteByIdFromDatabase"() {
    }
}
