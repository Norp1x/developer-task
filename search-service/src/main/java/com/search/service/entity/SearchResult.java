package com.search.service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Entity class representing a search result.
 * This class maps to the "search_results" table in the database and stores details about each search result,
 * including title, link, and date.
 */
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

    @NotBlank(message = "Title cannot be blank")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Link cannot be blank")
    @Column(name = "link")
    private String link;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
