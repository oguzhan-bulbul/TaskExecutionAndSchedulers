package com.ouz.simpleasynctaskexecutor.tasks;

public class SaveTask implements Runnable {
  private int taskNumber;

  public SaveTask(int taskNumber) {
    this.taskNumber = taskNumber;
  }

  public void save() throws InterruptedException {
    System.out.println(
        Thread.currentThread().getName()
            + " == "
            + this.taskNumber
            + " numarali kayit islemi gerceklesiyor.");
    Thread.sleep(3000);
  }

  @Override
  public void run() {
    try {
      save();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
