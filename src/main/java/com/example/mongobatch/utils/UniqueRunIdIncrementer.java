package com.example.mongobatch.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

@Slf4j
public class UniqueRunIdIncrementer extends RunIdIncrementer {
    private static final String RUN_ID = "run-id";

    @Override
    public JobParameters getNext(JobParameters parameters) {
        if (parameters==null || parameters.isEmpty()) {
            return new JobParametersBuilder().addLong(RUN_ID, 1L).toJobParameters();
        }
        Long id = parameters.getLong(RUN_ID, 1L) + 1;
        return new JobParametersBuilder().addLong(RUN_ID, id).toJobParameters();
    }
}
