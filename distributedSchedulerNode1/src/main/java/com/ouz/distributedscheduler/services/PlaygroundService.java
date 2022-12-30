package com.ouz.distributedscheduler.services;

import com.ouz.distributedscheduler.jobs.FirstJob;
import com.ouz.distributedscheduler.jobs.SecondJob;
import com.ouz.distributedscheduler.jobs.ThirdJob;
import com.ouz.distributedscheduler.timer.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaygroundService {

  private final SchedulerService schedulerService;

  @Autowired
  public PlaygroundService(final SchedulerService schedulerService) {
    this.schedulerService = schedulerService;
  }

  public void runFirstJob() {

    final TimerInfo timerInfo = new TimerInfo();
    timerInfo.setRunForever(true);
    timerInfo.setInitialOffsetMs(0);
    timerInfo.setRepeatIntervalMs(3000);
    timerInfo.setCallbackData("This is FIRST JOB call back data.");

    schedulerService.scheduleWithSimpleScheduler(FirstJob.class, timerInfo);
  }

  public void runSecondJob() {

    schedulerService.scheduleWithCronScheduler(SecondJob.class, "*/5 * * ? * *");
  }

  public void runThirdJob() {

    final TimerInfo timerInfo = new TimerInfo();
    timerInfo.setTotalFireCount(10);
    timerInfo.setRemainingFireCount(timerInfo.getTotalFireCount());
    timerInfo.setInitialOffsetMs(0);
    timerInfo.setRepeatIntervalMs(5000);
    timerInfo.setCallbackData("This is THIRD JOB call back data.");

    schedulerService.scheduleWithSimpleScheduler(ThirdJob.class, timerInfo);
  }

  public List<TimerInfo> getAllRunningTimers() {
    return schedulerService.getAllRunningTimers();
  }

  public TimerInfo getTimerInfoById(final String id) {
    return schedulerService.getTimerInfoById(id);
  }

  public Boolean deleteTimer(final String id) {
    return schedulerService.deleteJob(id);
  }
}
