package com.ouz.simpleasynctaskexecutor.service;

import com.ouz.simpleasynctaskexecutor.tasks.SaveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskExecutor taskExecutor;

    public void doSaves() {
        for (int i = 0; i < 30; i++) {
            taskExecutor.execute(new SaveTask(i));
        }
    }

    @Async("simpleAsyncTaskExecutor")
    public void doSaves2() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName()
                        + " == "
                        + " kayit yapiliyor");

        Thread.sleep(3000);
    }

    @Async("simpleAsyncTaskExecutor")
    public void doSaves3() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName()
                        + " == "
                        + " kayit yapiliyor");
        Thread.sleep(3000);

    }

    @Async("simpleAsyncTaskExecutor")
    public void doSaves4() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName()
                        + " == "
                        + " kayit yapiliyor");

        Thread.sleep(3000);

    }
}
