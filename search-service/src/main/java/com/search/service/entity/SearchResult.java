package com.search.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "search_results")
public class SearchResult {

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

    @NonNull
    @Column(name = "date")
    private Date date;

}
