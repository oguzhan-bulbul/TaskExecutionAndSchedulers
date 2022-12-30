package com.ouz.threadpooltaskexecutor.service;

import com.ouz.threadpooltaskexecutor.tasks.SaveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired TaskExecutor taskExecutor;

  public void doSaves() {
    for (int i = 0; i < 40; i++) {
      taskExecutor.execute(new SaveTask(i));
    }
  }
}
