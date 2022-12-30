package com.ouz.taskschedulingwithannotation.tasks;

import java.util.Date;

public class ScheduledTask {

  private int taskNumber;

  public ScheduledTask(int taskNumber) {
    this.taskNumber = taskNumber;
  }

  public void save() throws InterruptedException {
    System.out.println(
            Thread.currentThread().getName()
                    + " == "
                    + this.taskNumber
                    + " numarali kayit islemi gerceklesiyor." + " Execute time = " + new Date());
    Thread.sleep(3000);
  }


}
