package com.search.service.repository;

import com.search.service.entity.SearchResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Repository interface for performing CRUD operations on {@link SearchResult} entities.
 * This interface extends {@link CrudRepository} and provides basic CRUD functionality
 * for the {@link SearchResult} entity.
 */
@Repository
public interface SearchRepository extends CrudRepository<SearchResult, Long> {

    List<SearchResult> findTop50ByOrderByIdDesc();
}
