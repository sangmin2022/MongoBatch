package com.example.mongobatch.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import eu.europeana.batch.config.MongoBatchConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Slf4j
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    public Datastore batchDatastore() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri)).build();

        Datastore datastore = Morphia.createDatastore(MongoClients.create(mongoClientSettings), databaseName);
        datastore.getMapper().mapPackage(PackageMapper.class.getPackageName());
        datastore.ensureIndexes();
        return datastore;
    }

    @Bean
    public MongoBatchConfigurer mongoBatchConfigurer() {
        log.info("mongoBatchConfigurer bean 만들기 시작");
        return new MongoBatchConfigurer(batchDatastore(), new SimpleAsyncTaskExecutor());
    }
}

