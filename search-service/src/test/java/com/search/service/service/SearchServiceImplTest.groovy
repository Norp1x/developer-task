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

    private static final String TEST_QUERY_VALUE = "test"
    private static final String FIRST_TEST_TITLE_VALUE = "firstTestTitle"
    private static final String FIRST_TEST_LINK_VALUE = "firstTestLink"
    private static final String SECOND_TEST_TITLE_VALUE = "secondTestTitle"
    private static final String SECOND_TEST_LINK_VALUE = "secondTestLink"

    def restTemplate = Mock(RestTemplate)
    def searchRepository = Mock(SearchRepository)
    def mapper = Mock(SearchResultsDtoToSearchResultMapper)
    def config = Mock(SearchServiceConfig)
    def searchService = new SearchServiceImpl(config, restTemplate, searchRepository, mapper)

    def "Should return list of results when search query requested and save response to database"() {
        given:
        def testQuery = TEST_QUERY_VALUE
        SearchResultsListDto searchResultsListDto = new SearchResultsListDto(List.of(
                prepareFirstExampleSearchResults(), prepareSecondExampleSearchResults()))
        def responseEntity = ResponseEntity.ok(searchResultsListDto)

        when:
        restTemplate.getForEntity(_ as String, SearchResultsListDto.class) >> responseEntity
        def resultList = searchService.search(testQuery)

        then:
        resultList.size() == 2
        resultList[0].title == FIRST_TEST_TITLE_VALUE
        resultList[0].link == FIRST_TEST_LINK_VALUE
        resultList[1].title == SECOND_TEST_TITLE_VALUE
        resultList[1].link == SECOND_TEST_LINK_VALUE
        1 * searchRepository.saveAll(_)
    }

    def "Should call 'save' method from searchRepository when using 'saveResultInDatabase' method from searchService"() {
        given:
        def searchResults = new SearchResult()

        when:
        searchService.saveResultInDatabase(searchResults)

        then:
        1 * searchRepository.save(_)
    }

    def "GetResultFromDatabase"() {
    }

    def "GetAllResultsFromDatabase"() {
    }

    def "DeleteByIdFromDatabase"() {
    }


    SearchResultsDto prepareFirstExampleSearchResults() {
        SearchResultsDto searchResultsDto = SearchResultsDto.builder()
                .title(FIRST_TEST_TITLE_VALUE)
                .link(FIRST_TEST_LINK_VALUE)
                .build()
        searchResultsDto
    }

    SearchResultsDto prepareSecondExampleSearchResults() {
        SearchResultsDto searchResultsDto = SearchResultsDto.builder()
                .title(SECOND_TEST_TITLE_VALUE)
                .link(SECOND_TEST_LINK_VALUE)
                .build()
        searchResultsDto
    }
}
