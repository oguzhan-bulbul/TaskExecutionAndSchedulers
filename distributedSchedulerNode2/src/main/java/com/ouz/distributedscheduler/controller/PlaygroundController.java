package com.ouz.distributedscheduler.controller;

import com.ouz.distributedscheduler.services.PlaygroundService;
import com.ouz.distributedscheduler.timer.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class PlaygroundController {

  private PlaygroundService playgroundService;

  @Autowired
  public PlaygroundController(final PlaygroundService playgroundService) {
    this.playgroundService = playgroundService;
  }

  @PostMapping("/runFirstJob")
  public void runFirstJob() {
    playgroundService.runFirstJob();
  }

  @PostMapping("/runSecondJob")
  public void runSecondJob() {
    playgroundService.runSecondJob();
  }

  @PostMapping("/runThirdJob")
  public void runThirdJob() {
    playgroundService.runThirdJob();
  }

  @GetMapping
  public List<TimerInfo> getAllRunningTimers() {
    return playgroundService.getAllRunningTimers();
  }

  @GetMapping("/{id}")
  public TimerInfo getTimerInfoById(@PathVariable String id) {
    return playgroundService.getTimerInfoById(id);
  }
}
