package com.ouz.taskschedulingwithannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskSchedulingWithAnnotationApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskSchedulingWithAnnotationApplication.class, args);
  }
}
