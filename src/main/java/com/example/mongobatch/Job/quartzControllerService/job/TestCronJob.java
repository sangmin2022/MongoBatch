package com.example.mongobatch.Job.quartzControllerService.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestCronJob extends QuartzJobBean {
    @Autowired
    @Qualifier("rpwTestJob")
    private Job job;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) {

        JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer)
                .getNextJobParameters(this.job)
                .toJobParameters();

        try {
            this.jobLauncher.run(this.job, jobParameters);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//        int jobId = jobDataMap.getInt("jobId");
//        JobKey jobKey = context.getJobDetail().getKey();
//
//        currThread = Thread.currentThread();
//        log.info("============================================================================");
//        log.info("CronJob started :: sleep : {} jobId : {} jobKey : {} - {}", MAX_SLEEP_IN_SECONDS, jobId, jobKey, currThread.getName());
//
//        IntStream.range(0, 10).forEach(i -> {
//            log.info("CronJob Counting - {}", i);
//            try {
//                TimeUnit.SECONDS.sleep(MAX_SLEEP_IN_SECONDS);
//            } catch (InterruptedException e) {
//                log.error(e.getMessage(), e);
//            }
//        });
//        log.info("CronJob ended :: jobKey : {} - {}", jobKey, currThread.getName());
//        log.info("============================================================================");
    }
}
