package com.example.taskscheduling.service;

import com.example.taskscheduling.tasks.SaveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class SchedulerService {

    @Autowired
    TaskScheduler taskScheduler;

    public void scheduleSaves() {
        for (int i = 0; i < 5; i++) {
            taskScheduler.schedule(new SaveTask(i), new CronTrigger("*/5 * * * * *"));
//      taskScheduler.scheduleAtFixedRate(new SaveTask(i), Instant.now(), Duration.ofMillis(3000));
//      taskScheduler.scheduleWithFixedDelay(new SaveTask(i), Instant.now(), Duration.ofMillis(3000));
        }
    }
}
