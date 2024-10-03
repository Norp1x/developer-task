package com.search.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "search_results")
public class SearchResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "link")
    private String link;

}
