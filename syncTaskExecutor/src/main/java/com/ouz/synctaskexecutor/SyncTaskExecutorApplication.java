package com.ouz.synctaskexecutor;

import com.ouz.synctaskexecutor.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SyncTaskExecutorApplication {

  public static void main(String[] args) {

    ApplicationContext context = SpringApplication.run(SyncTaskExecutorApplication.class, args);
    TaskService taskService = (TaskService) context.getBean("taskService");
    taskService.doSaves();
  }
}
