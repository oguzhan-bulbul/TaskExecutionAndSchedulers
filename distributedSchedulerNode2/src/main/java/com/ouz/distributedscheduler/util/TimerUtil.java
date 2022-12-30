package com.ouz.distributedscheduler.util;

import com.ouz.distributedscheduler.timer.TimerInfo;
import org.quartz.*;

import java.util.Date;

public final class TimerUtil {

  public static JobDetail buildJobDetailForSimpleScheduler(
      final Class jobClass, final TimerInfo info) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("totalFireCount", info.getTotalFireCount());
    jobDataMap.put("remainingFireCount", info.getRemainingFireCount());
    jobDataMap.put("repeatIntervalMs", info.getRepeatIntervalMs());
    jobDataMap.put("initialOffsetMs", info.getInitialOffsetMs());
    jobDataMap.put("callbackData", info.getCallbackData());
    jobDataMap.put("runForever", info.isRunForever());

    return JobBuilder.newJob(jobClass)
        .withIdentity(jobClass.getSimpleName())
        .setJobData(jobDataMap)
        .build();
  }

  public static JobDetail buildJobDetailForCronScheduler(
      final Class jobClass, final String cronExpression) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put(jobClass.getSimpleName(), cronExpression);

    return JobBuilder.newJob(jobClass)
        .withIdentity(jobClass.getSimpleName())
        .setJobData(jobDataMap)
        .build();
  }

  public static Trigger buildSimpleTrigger(final Class jobClass, final TimerInfo timerInfo) {

    SimpleScheduleBuilder builder =
        SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInMilliseconds(timerInfo.getRepeatIntervalMs());

    if (timerInfo.isRunForever()) {
      builder = builder.repeatForever();
    } else {
      builder = builder.withRepeatCount(timerInfo.getTotalFireCount());
    }

    return TriggerBuilder.newTrigger()
        .withIdentity(jobClass.getSimpleName())
        .withSchedule(builder)
        .startAt(new Date(System.currentTimeMillis() + timerInfo.getInitialOffsetMs()))
        .build();
  }

  public static Trigger buildCronTrigger(final Class jobClass, final String cronExpression) {

    CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);

    return TriggerBuilder.newTrigger()
        .withIdentity(jobClass.getSimpleName())
        .withSchedule(builder)
        .startNow()
        .build();
  }
}
