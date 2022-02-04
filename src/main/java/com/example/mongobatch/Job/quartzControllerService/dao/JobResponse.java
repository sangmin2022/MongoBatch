package com.example.mongobatch.Job.quartzControllerService.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResponse {
    private String jobName;
    private String groupName;
    private String jobStatus;
    private String scheduleTime;
    private String lastFiredTime;
    private String nextFireTime;
}