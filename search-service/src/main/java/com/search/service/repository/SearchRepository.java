package com.search.service.repository;

import com.search.service.entity.SearchResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends CrudRepository<SearchResult, Long> {

}
