package com.ouz.threadpooltaskexecutor;

import com.ouz.threadpooltaskexecutor.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ThreadPoolTaskExecutorApplication {

  public static void main(String[] args) {
    ApplicationContext context =
        SpringApplication.run(ThreadPoolTaskExecutorApplication.class, args);
    TaskService taskService = (TaskService) context.getBean("taskService");
    taskService.doSaves();
  }
}
