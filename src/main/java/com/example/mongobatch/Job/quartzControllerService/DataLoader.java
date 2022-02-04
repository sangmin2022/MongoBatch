package com.example.mongobatch.Job.quartzControllerService;

import com.example.mongobatch.Job.quartzControllerService.dao.JobRequest;
import com.example.mongobatch.Job.quartzControllerService.job.TestCronJob;
import com.example.mongobatch.Job.quartzControllerService.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        JobRequest jobRequest = new JobRequest();
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("jobID", "1");

        jobRequest.setJobName("rpwTestJob");
        jobRequest.setCronExpression("0 0/1 * * * ?");
        jobRequest.setJobDataMap(jobDataMap);
        scheduleService.addJob(jobRequest, TestCronJob.class);

    }
}
