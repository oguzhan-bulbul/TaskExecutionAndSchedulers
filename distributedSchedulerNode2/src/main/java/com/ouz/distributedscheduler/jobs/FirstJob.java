package com.ouz.distributedscheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Olusturmak istedigimiz Job Quartz'in Job interface'ini implemente etmeli
 *
 * <p>jobExecution nesnesi uzerinden Job'a ozel olarak sakladigimiz datalari cagirip kullanabiliriz.
 */
public class FirstJob implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    String callbackData = (String) jobDataMap.get("callbackData");

    System.out.println("First Job runs at : " + new Date() + "callbackData : " + callbackData);
  }
}
