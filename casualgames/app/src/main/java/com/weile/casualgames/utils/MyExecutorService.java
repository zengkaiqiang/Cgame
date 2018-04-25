package com.weile.casualgames.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutorService {
    //线程池
    private ExecutorService cachedThreadPool ;

    //TODO 建议做成单例模式
    private MyExecutorService()
    {
        cachedThreadPool = Executors.newCachedThreadPool();
    }

    private static MyExecutorService instance = null;


    public static MyExecutorService getInstance() {
        if (instance == null) { // 延迟加载
            synchronized (MyExecutorService.class) { // 同步锁
                if (instance == null)
                    instance = new MyExecutorService();
            }
        }
        return instance;
    }

    /**
     * 将线程加入线程池
     *
     * @param runnable
     */
    public void initCachedThreadPool(Runnable runnable) {
        if (cachedThreadPool.isShutdown()) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        cachedThreadPool.execute(runnable);
    }
    /**
     * 释放空闲的线程interrupt()
     * */
    public void shutdownThreadPool()
    {
        if(cachedThreadPool!=null)
            cachedThreadPool.shutdown();
    }


}
