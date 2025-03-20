package com.search.service.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Norpix on 26.12.2024.
 * Description:
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "txt-files")
public class TxtFile {

    @Id
    private String id;

    private String fileName;
    private Binary fileContent;
}
