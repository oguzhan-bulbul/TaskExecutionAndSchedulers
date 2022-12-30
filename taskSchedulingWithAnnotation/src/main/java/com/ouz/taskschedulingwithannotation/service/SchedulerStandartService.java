package com.ouz.taskschedulingwithannotation.service;

import com.ouz.taskschedulingwithannotation.tasks.ScheduledTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class SchedulerStandartService {

  /**
   *
   * <p>FixedRate ifadesi verilen deger methodun baslangic suresinden itibaren kac saniyede bir
   * calisacagini ifade eder.
   *
   * <p>FixedDelay ifadesi methodun bitis suresinden itibaren kac saniyede bir calisacagini ifade
   * eder.
   *

   *
   * @throws InterruptedException
   */
  @Scheduled(fixedRate = 5000)
  public void saveScheduler1() throws InterruptedException {
    ScheduledTask scheduledTasks = new ScheduledTask(1);
    scheduledTasks.save();
  }

  @Scheduled(fixedDelay = 5000)
  public void saveScheduler2() throws InterruptedException {
    ScheduledTask scheduledTasks = new ScheduledTask(2);
    scheduledTasks.save();
  }

  @Scheduled(fixedDelay = 5000)
  public void saveScheduler3() throws InterruptedException {
    ScheduledTask scheduledTasks = new ScheduledTask(3);
    scheduledTasks.save();
  }

  @Scheduled(fixedDelay = 5000)
  public void saveScheduler4() throws InterruptedException {
    ScheduledTask scheduledTasks = new ScheduledTask(4);
    scheduledTasks.save();
  }

  @Scheduled(fixedDelay = 5000)
  public void saveScheduler5() throws InterruptedException {
    ScheduledTask scheduledTasks = new ScheduledTask(5);
    scheduledTasks.save();
  }
}
