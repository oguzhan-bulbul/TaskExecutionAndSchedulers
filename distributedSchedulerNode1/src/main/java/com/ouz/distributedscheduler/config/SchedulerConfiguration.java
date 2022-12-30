package com.ouz.distributedscheduler.config;

import com.ouz.distributedscheduler.services.SimpleTriggerListener;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class SchedulerConfiguration {

  @Bean
  public JobFactory jobFactory(ApplicationContext applicationContext) {
    AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);

    return jobFactory;
  }

  /**
   * Schedulerimizin otomatize bir sekilde istedigimiz ayarlarla olusmasini saglayan SchedulerFactoryBean nesnemizi springe olusturuyoruz.
   * @param jobFactory
   * @param triggers
   * @return SchedulerFactoryBean
   * @throws Exception
   */

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger[] triggers)
      throws Exception {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setOverwriteExistingJobs(true);
    factory.setAutoStartup(true);
    factory.setJobFactory(jobFactory);
    factory.setQuartzProperties(quartzProperties());
    factory.setTriggers(triggers);
    return factory;
  }

  /**
   * <p> quartz.properties uzerinden okunan ayarlarin schedulerimiza yansimasi icin olusturulan Properties nesnesi
   * @return
   * @throws IOException
   */

  private Properties quartzProperties() throws IOException {
    InputStream inputStream = new ClassPathResource("/quartz.properties").getInputStream();
    final Properties properties = new Properties();
    properties.load(inputStream);
    inputStream.close();
    return properties;
  }
}
