package com.ouz.taskschedulingwithannotation.service;

import com.ouz.taskschedulingwithannotation.tasks.ScheduledTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerCronService {

    /**
     * <p>Cron ifadesi FixedRate olarak calisir. Cron ifadeleri calisma mantigi olarak kac saniyede
     * bir calisayim gibi bir mantiga hizmet etmez. Cron ifadeleri calisma mantigi olarak hangi yilin
     * hangi ayinin hangi gununun hangi saatinin hangi dakikasinin hangi saniyesinde calisacagini
     * ifade eder.
     *
     * @throws InterruptedException
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void saveScheduler1() throws InterruptedException {
        ScheduledTask scheduledTasks = new ScheduledTask(1);
        scheduledTasks.save();
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void saveScheduler2() throws InterruptedException {
        ScheduledTask scheduledTasks = new ScheduledTask(2);
        scheduledTasks.save();
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void saveScheduler3() throws InterruptedException {
        ScheduledTask scheduledTasks = new ScheduledTask(3);
        scheduledTasks.save();
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void saveScheduler4() throws InterruptedException {
        ScheduledTask scheduledTasks = new ScheduledTask(4);
        scheduledTasks.save();
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void saveScheduler5() throws InterruptedException {
        ScheduledTask scheduledTasks = new ScheduledTask(5);
        scheduledTasks.save();
    }
}
