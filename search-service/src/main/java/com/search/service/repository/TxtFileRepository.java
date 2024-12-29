package com.search.service.repository;

import com.search.service.entity.TxtFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TxtFileRepository extends MongoRepository<TxtFile, String> {
}
