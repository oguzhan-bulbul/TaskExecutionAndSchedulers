package com.ouz.distributedscheduler.services;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class SimpleTriggerListener implements TriggerListener {

  private SchedulerService schedulerService;

  public SimpleTriggerListener(final SchedulerService schedulerService) {
    this.schedulerService = schedulerService;
  }

  @Override
  public String getName() {
    return SimpleTriggerListener.class.getSimpleName();
  }

  @Override
  public void triggerFired(final Trigger trigger, final JobExecutionContext jobExecutionContext) {

    String id = trigger.getKey().getName();
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    Boolean isRunForever = (Boolean) jobDataMap.get("runForever");
    Integer remainingFireCount = (Integer) jobDataMap.get("remainingFireCount");

    if (!isRunForever) {
      if (remainingFireCount == 0) {
        return;
      }

      remainingFireCount = remainingFireCount - 1;
      schedulerService.updateTimer(id, "remainingFireCount", remainingFireCount);
    }
  }

  @Override
  public boolean vetoJobExecution(
      final Trigger trigger, final JobExecutionContext jobExecutionContext) {
    return false;
  }

  @Override
  public void triggerMisfired(final Trigger trigger) {}

  @Override
  public void triggerComplete(
      final Trigger trigger,
      final JobExecutionContext jobExecutionContext,
      final Trigger.CompletedExecutionInstruction completedExecutionInstruction) {}
}
