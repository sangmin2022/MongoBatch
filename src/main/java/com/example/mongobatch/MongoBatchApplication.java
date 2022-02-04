package com.example.mongobatch;

import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MongoBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoBatchApplication.class, args);


//        MongoTemplate mongoTemplate = new MongoTemplate(
//                MongoClients.create("mongodb+srv://sangmin-jo:osU70wcnASf624Xs@dev-aibizon.nbjts.mongodb.net")
//                , "test_db");
//
//        mongoTemplate.getCollection("ExecutionContext").deleteMany(new Document());
//        mongoTemplate.getCollection("JobExecution").deleteMany(new Document());
//        mongoTemplate.getCollection("JobInstance").deleteMany(new Document());
//        mongoTemplate.getCollection("Sequence").deleteMany(new Document());
//        mongoTemplate.getCollection("StepExecution").deleteMany(new Document());
//        mongoTemplate.getCollection("save_table").deleteMany(new Document());
//
//        System.out.println(">>>>> Job metadata for mongoDB in test_db is all deleted!!");
    }
}
