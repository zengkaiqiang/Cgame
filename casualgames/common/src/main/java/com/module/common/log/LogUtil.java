package com.module.common.log;

import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Description: Logs封装类，用于将log输出到本地保存
 *
 */
public class LogUtil {

    private static final String TAG = "LogUtil";

    // define log level tag
    private static final String LEVEL_V = "V";
    private static final String LEVEL_D = "D";
    private static final String LEVEL_I = "I";
    private static final String LEVEL_W = "W";
    private static final String LEVEL_E = "E";

    private static boolean DEBUG = true;

    private static final ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(
            0, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new PriorityThreadFactory(TAG, Process.THREAD_PRIORITY_BACKGROUND),
            new RejectedExecutionHandler() {

                @Override
                public void rejectedExecution(Runnable r,
                                              ThreadPoolExecutor executor) {
                    if (r instanceof FutureTask<?>) {
                        ((FutureTask<?>) r).cancel(true);
                    }
                    // 注意，使用Log，别用LogF，否则会死循环
                    if (LogUtil.DEBUG) {
                        Log.d(TAG, "mExecutor.rejectedExecution.r = " + r);
                    }
                }
            });

    public static void setDebug(boolean isDebug) {
        DEBUG = isDebug;
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    /**
     * 打印verbose级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @return The number of bytes written.
     */
    public static int v(String tag, String msg) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_V, tag, msg, null);
        return Log.v(tag, msg);
    }

    /**
     * 打印verbose级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     * @return The number of bytes written.
     */
    public static int v(String tag, String msg, Throwable tr) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_V, tag, msg, tr);
        return Log.v(tag, msg, tr);
    }

    /**
     * 打印debug级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @return The number of bytes written.
     */
    public static int d(String tag, String msg) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_D, tag, msg, null);
        return Log.d(tag, msg);
    }

    /**
     * 打印debug级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     * @return The number of bytes written.
     */
    public static int d(String tag, String msg, Throwable tr) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_D, tag, msg, tr);
        return Log.d(tag, msg, tr);
    }

    /**
     * 打印info级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @return The number of bytes written.
     */
    public static int i(String tag, String msg) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_I, tag, msg, null);
        return Log.i(tag, msg);
    }

    /**
     * 打印info级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     * @return The number of bytes written.
     */
    public static int i(String tag, String msg, Throwable tr) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_I, tag, msg, tr);
        return Log.i(tag, msg, tr);
    }

    /**
     * 打印warning级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @return The number of bytes written.
     */
    public static int w(String tag, String msg) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_W, tag, msg, null);
        return Log.w(tag, msg);
    }

    /**
     * 打印warning级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     * @return The number of bytes written.
     */
    public static int w(String tag, String msg, Throwable tr) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_W, tag, msg, tr);
        return Log.w(tag, msg, tr);
    }

    /**
     * 打印error级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @return The number of bytes written.
     */
    public static int e(String tag, String msg) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_E, tag, msg, null);
        return Log.e(tag, msg);
    }

    /**
     * 打印error级别的日志
     *
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     * @return The number of bytes written.
     */
    public static int e(String tag, String msg, Throwable tr) {
        if (!DEBUG) {
            return -1;
        }

        if (null == msg) {
            msg = "null";
        }
        writeToFile(LEVEL_E, tag, msg, tr);
        return Log.e(tag, msg, tr);
    }

    public static void flush() {
        mExecutor.getQueue().clear();
    }

    public static void destory() {
        if (!mExecutor.isShutdown()) {
            mExecutor.shutdown();
        }
    }

    private static void writeToFile(final String level, final String tag,
                                    final String msg, final Throwable tr) {
        mExecutor.execute(new FutureTask<Void>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                if (Environment.MEDIA_MOUNTED.equals(Environment
                        .getExternalStorageState())) {
                    // StringBuffer sb = new StringBuffer();
                    // sb.append(level).append(" ").append(new
                    // SimpleDateFormat(Config.Format.LOG_MSG_DATE_FORMAT).format(new
                    // Date())).append(": ").append(android.os.Process.myPid()).append(" ")
                    // .append(android.os.Process.myTid()).append(" ").append(tag).append(" ").append(msg);
                    // if (tr != null) {
                    // sb.append(System.getProperties().getProperty("line.separator")).append(Log.getStackTraceString(tr));
                    // }
                    // sb.append(System.getProperties().getProperty("line.separator"));
                    // Config.getPublicDir(Config.DIR_LOG)必须动态去获取值，否则从不登录到登录后，log还是写不到userId对应的目录下；
                    //
                    // File file = new File(Config.getPublicDir(Config.DIR_LOG),
                    // "log-" + new
                    // SimpleDateFormat(Config.Format.LOG_FILE_NAME_DATE_FORMAT).format(new
                    // Date())
                    // + Config.FILE_NAME_EXTENSION_LOG);
                    // writeStringToFile(file, sb.toString(), true);
                    // sb.setLength(0);
                }
                return null;
            }
        }) {
        });
    }

    /**
     * 写字符串到文件，文件父目录如果不存在，会自动创建
     *
     * @param file
     * @param content
     * @param isAppend
     * @return
     */
    private static boolean writeStringToFile(File file, String content,
                                             boolean isAppend) {
        boolean isWriteOk = false;
        char[] buffer = null;
        int count = 0;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (file.exists()) {
                br = new BufferedReader(new StringReader(content));
                bw = new BufferedWriter(new FileWriter(file, isAppend));
                buffer = new char[1024];
                int len = 0;
                while ((len = br.read(buffer, 0, 1024)) != -1) {
                    bw.write(buffer, 0, len);
                    count += len;
                }
                bw.flush();
            }
            isWriteOk = content.length() == count;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    bw = null;
                }
                if (br != null) {
                    br.close();
                    br = null;
                }
                buffer = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isWriteOk;
    }

}