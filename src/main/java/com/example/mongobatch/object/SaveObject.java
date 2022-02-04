package com.example.mongobatch.object;

import dev.morphia.annotations.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "save_table")
public class SaveObject {
    @Id
    private String id;

    private String info;
    private int count;
}
