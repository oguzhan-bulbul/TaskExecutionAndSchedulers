package com.ouz.executorservice.service;

import com.ouz.executorservice.tasks.SaveTask;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TaskService {

    /**
     * <p> Executors.newFixedThreadPool(int i) metodu verilen i degerinde sabit ayarlanan
     * corePoolSize ve maxPoolSize degerlerine sahip bir ThreadPoolExecutor dondurur.
     * Her durumda Pooldaki thread miktari i miktari kadardir.
     * <p> Eger fazla task gelirse bunlar siniri olmayan bir queue icerisinde threadler gelip alana kadar bekler.
     * <p>Executors.newFixedThreadPool(int i,ThreadFactory threadFactory) metodu ise ayni ozelliklere sahiptir.
     * Tek farki olusturacagi threadleri bizim verdigimiz ThreadFactory'den olusturur.
     *
     * <p>Executors.newCachedThreadPool() metodu ne kadar threade ihtiyaci varsa o kadar thread olusturan bir ThreadPoolExecutor doner.
     * <p> Burada dikkat edilmesi gereken durumlardan birisi olusturulan bu ThreadPoolExecutor eger elinde olusmus bir thread varsa
     * onu tekrar kullanacaktir.
     * <p>Executors.newSingleThreadExecutor(ThreadFactory threadFactory) metodu ise ayni ozellikleri tasir.
     * Fakat thread olusturmak icin verdigimiz ThreadFactory'i kullanir.
     *
     * <p>Executors.newSingleThreadExecutor() metodu corePool size maxPoolSize ozellikleri 1 olan ve sinirsiz bi queue'ya sahip olan
     * bir ThreadPool dondurur.
     * <p>Fakat herhangi bir sekilde bu thread execution zamaninda kendi kapanmadan engellenirse , yeni bir
     * thread olusur ve tasklari devam ettirir.
     *
     * <p>Executors.newWorkStealingPool() metodu sistemde bulunan musait islemci degerine bakarak ForkJoinPool dondurur.
     * <p>Executors.newWorkStealingPool(int parallelism) metodu verilen parallelism degerine sahip bir ForkJoinPool dondurur.
     *
     * <p>
     **/
    ExecutorService newFixedThreadPoolExecutorService = Executors.newFixedThreadPool(10);
    ExecutorService newCachedThreadPoolExecutorService = Executors.newCachedThreadPool();
    ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
    ExecutorService workStealingPoolExecutorService = Executors.newWorkStealingPool();
    ExecutorService workStealingPoolWithGivenParallelismLevelExecutorService = Executors.newWorkStealingPool(4);
    ExecutorService virtualThreads = Executors.newVirtualThreadPerTaskExecutor();


    public void doSaves() {
        for (int i = 0; i < 30; i++) {
            virtualThreads.execute(new SaveTask(i));
        }

    }
}
