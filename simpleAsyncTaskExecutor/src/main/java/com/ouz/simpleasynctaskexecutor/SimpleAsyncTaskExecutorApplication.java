package com.ouz.simpleasynctaskexecutor;

import com.ouz.simpleasynctaskexecutor.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SimpleAsyncTaskExecutorApplication {

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext context =
        SpringApplication.run(SimpleAsyncTaskExecutorApplication.class, args);
    TaskService taskService = (TaskService) context.getBean("taskService");
    taskService.doSaves();
//    taskService.doSaves2();
//    taskService.doSaves3();
//    taskService.doSaves4();
  }
}
