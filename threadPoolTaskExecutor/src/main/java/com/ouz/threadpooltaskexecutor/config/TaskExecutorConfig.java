package com.ouz.threadpooltaskexecutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

  /**
   * <p>ThreadPoolTaskExecutor yapisi asenkron bir bicimde gonderilen tasklari calistirir. Bu yapilari
   * simple bean configuration kullanarak duzenleyebiliriz. ThreadPoolTaskExecutor yapisinda
   * belirttigimiz sinirlarda threadler olusturulur ve kullanilir.
   *
   * <p>ThreadPoolExecutor standart durumda olusturulacak Threadlerin sayisini corePoolSize ozelligi ile belirler.
   * <p>ThreadPoolExecutor corepoolsize dolu oldugu zaman siraya alinacak islerin sayisini QueueCapacity ozelligi ile belirler.
   * <p>ThreadPoolExecutor corePoolSize ve QueueCapacity dolu oldugu o zaman kullanilabilir Thread Sayisini artirmak icin
   * maxPoolSize ozelligini kullanilir. setMaxPoolSize ile bu durumda olusturulabilecek Thread sayisi belirlenir.
   *
   * <p>Default olarak core threadler bile sadece tasklar geldiginde olusturulur ve baslatilir.Fakat bu olay
   * prestartCoreThread() veya prestartAllCoreThreads() metotlariyla degistirilebilir.
   *
   * <p>Yeni threadler ThreadFactory kullanilarak yaratilir.
   * <p>Aksi belirtilmedikce default olarak Threadler ayni ThreadGroup'da ayni NORM_PRIORITY onceliginde
   * ve non-daemon durumunda yaratilir.
   * <p>ThreadPoolTaskExecutor yapisina baska bir ThreadFactory saglayarak, thread'in adini,grubunu,onceligini , daemon durumunu
   * degistirmesini saglayabiliriz. Bunlar icin set methodlari bulunmaktadir.
   *
   * <p>Eger pool'da corePoolSize'dan daha fazla thread varsa fazla olan threadler keepAliveTime parametresinden
   * daha uzun bosta kalirsa silinirler.
   * <p>Bu parametre dinamik olarak setKeepAliveTime() metodu ile degistirilebilir.
   *
   * <p>Default olarak corePoolSize'dan fazla thread oldugu durumlarda ilave threadler icin kullanilir
   * fakat allowCoreThreadTimeOut metodu ile corePoolSize icerisinde kalan threadler icinde
   * kullanilabilir hale getirilebilir.
   *
   * <p>Gonderilen task sayisi > queue capacity + maxPoolSize ise exception firlatilir.Threadlerin hepsini yonetemez.
   * <p>Gonderilen task sayisi < corePoolSize + queueSize ise MaxPoolSize devreye alinmaz.
   * <p>Core Pool Size kadar thread ayni anda calisarak queue'da isleri bitirir.
   *
   * @return
   */
  @Bean(name = "threadPoolTaskExecutor")
  public TaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5); // Olusturulacak Threadlerin sayisini belirler.
    executor.setQueueCapacity(35); // Queue,ya alinabilecek max task sayisi
    executor.setMaxPoolSize(10); // MaxPoolSize olusturulabilecek maksimum thread sayisini ifade eder.

    //executor.setKeepAliveSeconds(1);
    executor.afterPropertiesSet();
    return executor;
  }
}
