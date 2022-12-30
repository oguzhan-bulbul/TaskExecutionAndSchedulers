package com.ouz.distributedscheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.util.Date;

public class SecondJob implements Job {
  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    String cronExpression = (String) jobDataMap.get(SecondJob.class.getSimpleName());

    System.out.println(
        "Second Job runs at : " + new Date() + "with cron expression : " + cronExpression);
  }
}
