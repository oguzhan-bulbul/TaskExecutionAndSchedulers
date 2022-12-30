package com.ouz.synctaskexecutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class TaskExecutorConfig {

  /** SyncTaskExecutor yapisi senkron bir bicimde icerisine gelen tasklari sirayla calistirir. */

  @Bean(name = "syncTaskExecutor")
  public SyncTaskExecutor syncTaskExecutor() {
    SyncTaskExecutor syncTaskExecutor = new SyncTaskExecutor();
    return syncTaskExecutor;
  }
}
