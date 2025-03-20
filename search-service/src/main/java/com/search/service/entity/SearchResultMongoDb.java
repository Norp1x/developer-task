package com.search.service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Norpix on 25.12.2024.
 * Description:
 */
@Data
@AllArgsConstructor
@Document(collection = "search-service-standard")
public class SearchResultMongoDb {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @JsonProperty("_id")
    private ObjectId _id;

    @JsonProperty("idNumber")
    private Integer idNumber;

    @NotBlank(message = "Title cannot be blank")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "Link cannot be blank")
    @JsonProperty("link")
    private String link;

    @JsonProperty("date_time")
    private Instant dateTime = Instant.now();

    public SearchResultMongoDb() {
        this.idNumber = counter.getAndIncrement();
    }

    @Override
    public String toString() {
        return "id=" + idNumber + "\n" +
                ", title='" + title + '\'' + "\n" +
                ", link='" + link + '\'' + "\n" +
                ", dateTime=" + dateTime + "\n" +
                "\n";
    }
}
