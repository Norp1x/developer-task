package com.search.service.service.impl

import com.search.service.config.SearchServiceConfig
import com.search.service.dto.SearchResultsDto
import com.search.service.dto.SearchResultsListDto
import com.search.service.entity.SearchResult
import com.search.service.mapper.SearchResultsDtoToSearchResultMapper
import com.search.service.mapper.SearchResultsDtoToSearchResultMongoDbMapper
import com.search.service.repository.SearchMongoRepository
import com.search.service.repository.SearchRepository
import com.search.service.repository.TxtFileRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDateTime

class SearchServiceImplTest extends Specification {

    private static final String TEST_QUERY_VALUE = "test"
    private static final String FIRST_TEST_TITLE_VALUE = "firstTestTitle"
    private static final String FIRST_TEST_LINK_VALUE = "www.firstTestLink.com"
    private static final String SECOND_TEST_TITLE_VALUE = "secondTestTitle"
    private static final String SECOND_TEST_LINK_VALUE = "www.secondTestLink.com"

    def txtFileRepository = Mock(TxtFileRepository)
    def searchMongoRepository = Mock(SearchMongoRepository)
    def mongoDbMapper = Mock(SearchResultsDtoToSearchResultMongoDbMapper)
    def restTemplate = Mock(RestTemplate)
    def searchRepository = Mock(SearchRepository)
    def mapper = Mock(SearchResultsDtoToSearchResultMapper)
    def config = Mock(SearchServiceConfig)
    def searchService = new SearchServiceImpl(config, restTemplate, searchRepository, txtFileRepository, searchMongoRepository, mapper, mongoDbMapper)

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
        LocalDateTime dateTime = LocalDateTime.now()
        def searchResults = new SearchResult(null, FIRST_TEST_TITLE_VALUE, FIRST_TEST_LINK_VALUE, dateTime)

        when:
        searchService.saveResultInDatabase(searchResults)

        then:
        1 * searchRepository.save(searchResults)
    }

    def "GetResultFromDatabase"() {
    }

    def "GetAllResultsFromDatabase"() {
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
