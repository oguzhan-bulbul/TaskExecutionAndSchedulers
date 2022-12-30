package com.example.taskscheduling;

import com.example.taskscheduling.service.SchedulerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskSchedulingApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(TaskSchedulingApplication.class, args);

    SchedulerService schedulerService = (SchedulerService) context.getBean("schedulerService");
    schedulerService.scheduleSaves();
  }
}
