package com.example.mongobatch.object;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "test_table")
public class TestObject implements Serializable {
    @Id
    private String id;
    private String item;
}
