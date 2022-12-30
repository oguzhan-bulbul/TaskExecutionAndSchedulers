package com.ouz.distributedscheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class ThirdJob implements Job {
  @Override
  public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {

    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    String callbackData = (String) jobDataMap.get("callbackData");
    Integer remainingFireCount = (Integer) jobDataMap.get("remainingFireCount");

    System.out.println(
        "Third Job runs at : "
            + new Date()
            + "  callbackData : "
            + callbackData
            + " remainingFireCount : "
            + remainingFireCount);
  }
}
