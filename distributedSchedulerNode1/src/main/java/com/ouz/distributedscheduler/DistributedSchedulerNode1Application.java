package com.ouz.distributedscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistributedSchedulerNode1Application {

  public static void main(String[] args) {

    SpringApplication.run(DistributedSchedulerNode1Application.class, args);
  }
}
