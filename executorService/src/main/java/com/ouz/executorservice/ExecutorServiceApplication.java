package com.ouz.executorservice;

import com.ouz.executorservice.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExecutorServiceApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(ExecutorServiceApplication.class, args);
    TaskService taskService = (TaskService) context.getBean("taskService");
    taskService.doSaves();
  }
}
