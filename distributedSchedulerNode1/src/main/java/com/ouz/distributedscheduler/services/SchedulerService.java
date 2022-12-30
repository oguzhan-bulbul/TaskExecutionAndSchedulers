package com.ouz.distributedscheduler.services;

import com.ouz.distributedscheduler.timer.TimerInfo;
import com.ouz.distributedscheduler.util.TimerUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SchedulerService {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

//    @PostConstruct
//    public void init() throws SchedulerException {
//        scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener(this));
//    }

    /**
     * <p> TimerInfo classi uzerinden verilen propertyler ile calismasini istedigimiz Job icin JobDetail ve Trigger nesnelerini olusturup
     * schedulerimiza gonderdigimizde job baslar.
     *
     * @param jobClass
     * @param info
     */
    public void scheduleWithSimpleScheduler(final Class jobClass, final TimerInfo info) {
        final JobDetail jobDetail = TimerUtil.buildJobDetailForSimpleScheduler(jobClass, info);
        final Trigger trigger = TimerUtil.buildSimpleTrigger(jobClass, info);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * String olarak verilen cron ifadesine gore JobDetail ve Trigger olusturulup scheduler nesnesi uzerinden calistirilir.
     *
     * @param jobClass
     * @param cronExpression
     */
    public void scheduleWithCronScheduler(final Class jobClass, final String cronExpression) {
        final JobDetail jobDetail = TimerUtil.buildJobDetailForCronScheduler(jobClass, cronExpression);
        final Trigger trigger = TimerUtil.buildCronTrigger(jobClass, cronExpression);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p> Scheduler uzerinde calismakta olan Joblarin JobDetails nesneleri uzerinden JobDataMap icerisine ekledigimiz
     * Datalari cekebiliriz.
     * <p> Burada dikkat cekilmesi gereken yerlerden birisi GroupMatcher ile belli bir gruba ait Joblarida cekebiliyor olmamiz.
     *
     * @return TimerInfo nesnelerinden bir liste doner.
     */

    public List<TimerInfo> getAllRunningTimers() {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup()).stream()
                    .map(
                            jobKey -> {
                                try {
                                    final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                                    TimerInfo timerInfo = new TimerInfo();
                                    timerInfo.setRemainingFireCount(
                                            (Integer) jobDetail.getJobDataMap().get("remainingFireCount"));
                                    timerInfo.setRunForever((Boolean) jobDetail.getJobDataMap().get("runForever"));
                                    timerInfo.setRepeatIntervalMs(
                                            (Long) jobDetail.getJobDataMap().get("repeatIntervalMs"));
                                    timerInfo.setInitialOffsetMs(
                                            (Long) jobDetail.getJobDataMap().get("initialOffsetMs"));
                                    timerInfo.setCallbackData((String) jobDetail.getJobDataMap().get("callbackData"));
                                    timerInfo.setTotalFireCount(
                                            (Integer) jobDetail.getJobDataMap().get("totalFireCount"));
                                    return timerInfo;
                                } catch (SchedulerException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public TimerInfo getTimerInfoById(final String id) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(id));
            if (jobDetail == null) {
                return null;
            }
            TimerInfo timerInfo = new TimerInfo();
            timerInfo.setRemainingFireCount(
                    (Integer) jobDetail.getJobDataMap().get("remainingFireCount"));
            timerInfo.setRunForever((Boolean) jobDetail.getJobDataMap().get("runForever"));
            timerInfo.setRepeatIntervalMs((Long) jobDetail.getJobDataMap().get("repeatIntervalMs"));
            timerInfo.setInitialOffsetMs((Long) jobDetail.getJobDataMap().get("initialOffsetMs"));
            timerInfo.setCallbackData((String) jobDetail.getJobDataMap().get("callbackData"));
            timerInfo.setTotalFireCount((Integer) jobDetail.getJobDataMap().get("totalFireCount"));
            return timerInfo;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTimer(final String id, final String key, final Integer value) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(id));
            if (jobDetail == null) {
                System.out.println("Failed to find job with given id + " + id);
            }
            jobDetail.getJobDataMap().put(key, value);
            scheduler.addJob(jobDetail, true, true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public Boolean deleteJob(final String id) {
        try {
            return scheduler.deleteJob(new JobKey(id));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
