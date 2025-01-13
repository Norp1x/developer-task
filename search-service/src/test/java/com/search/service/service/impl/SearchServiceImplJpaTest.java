package com.search.service.service.impl;

import com.search.service.entity.SearchResult;
import com.search.service.repository.SearchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by  on 12.01.2025.
 * Description:
 */
@DataJpaTest
@ActiveProfiles("test")
class SearchServiceImplJpaTest {

    private static final String FIRST_TEST_TITLE_VALUE = "firstTestTitle";
    private static final String FIRST_TEST_LINK_VALUE = "www.firstTestLink.com";

    @Autowired
    SearchRepository searchRepository;

    @Test
    void testJpaSlice() {

        //given
        LocalDateTime dateTime = LocalDateTime.now();
        long countBefore = searchRepository.count();

        //when
        searchRepository.save(new SearchResult(null, FIRST_TEST_TITLE_VALUE, FIRST_TEST_LINK_VALUE, dateTime));
        long countAfter = searchRepository.count();

        //then
        assertThat(countBefore).isLessThan(countAfter + 1);
    }
}