package com.search.service.repository;

import com.search.service.entity.SearchResultMongoDb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchMongoRepository extends MongoRepository<SearchResultMongoDb, String> {
}
