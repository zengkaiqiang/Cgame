package com.module.common.log;

import android.os.Process;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @ClassName: PriorityThreadFactory
 *
 * @Description: A thread factory that creates threads with a given thread
 *               priority.
 *
 */
public class PriorityThreadFactory implements ThreadFactory {

    private static final int THREAD_PRIORITY_DEFAULT_VALUE = Process.THREAD_PRIORITY_DEFAULT
            + Process.THREAD_PRIORITY_LESS_FAVORABLE * 3;

    private final String mName;

    private final int mPriority;

    private final AtomicInteger mNumber = new AtomicInteger();

    public PriorityThreadFactory(String name) {
        this.mName = name;
        this.mPriority = THREAD_PRIORITY_DEFAULT_VALUE;
    }

    public PriorityThreadFactory(String name, int priority) {
        this.mName = name;
        this.mPriority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {

        return new Thread(r, "PriorityThreadFactory-" + mName + "-"
                + mNumber.getAndIncrement()) {
            @Override
            public void run() {

                if ("ExecutorSocketRequest".equals(mName)) {
                    Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT
                            + Process.THREAD_PRIORITY_MORE_FAVORABLE);

                } else if ("ExecutorUpload".equals(mName)) {
                    Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT);
                } else {
                    Process.setThreadPriority(mPriority);
                }

                super.run();
            }
        };
    }

}
