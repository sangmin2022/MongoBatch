package com.example.mongobatch.Job.quartzControllerService.service;

import com.example.mongobatch.Job.quartzControllerService.dao.JobRequest;
import com.example.mongobatch.Job.quartzControllerService.dao.JobStatusResponse;
import org.quartz.Job;
import org.quartz.JobKey;

public interface ScheduleService {
    JobStatusResponse getAllJobs();

    boolean isJobRunning(JobKey jobKey);

    boolean isJobExists(JobKey jobKey);

//    boolean addJob(JobRequest jobRequest, Class<? extends QuartzJobBean> jobClass);

    boolean addJob(JobRequest jobRequest, Class<? extends Job> jobClass);

    boolean deleteJob(JobKey jobKey);

    boolean pauseJob(JobKey jobKey);

    boolean resumeJob(JobKey jobKey);

    String getJobState(JobKey jobKey);
}
