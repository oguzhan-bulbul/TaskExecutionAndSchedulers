package com.ouz.simpleasynctaskexecutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class TaskExecutorConfig {

  /**
   * SimpleAsyncTaskExecutor threadleri yeniden kullanmaz. Her gonderilen task icin yeni bir thread
   * olusturur ve calistirir.
   *
   * <p>Fakat ConcurrencyLimit adi altinda bir ayarla ulasilabilecek paralel thread sayisini
   * belirlemeye izin verir.
   *
   * <p>Default halde concurrency limit sinirsizdir. Eger cok sayida az yasayan gorevleriniz varsa
   * Thread pool mekanizmasina sahip bir Executor kullanimi onerilir.
   */
  @Bean(name = "simpleAsyncTaskExecutor")
  public TaskExecutor simpleAsyncTaskExecutor() {
    SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
//    simpleAsyncTaskExecutor.setConcurrencyLimit(10); // Izin verilen paralel islem sayisi
    return simpleAsyncTaskExecutor;
  }
}
