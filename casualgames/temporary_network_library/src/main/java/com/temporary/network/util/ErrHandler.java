package com.temporary.network.util;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author 作者  zkq:
 * @version 创建时间：2016年10月10日 下午3:43:50
 * 类说明  
 */
public class ErrHandler implements UncaughtExceptionHandler {
	  /**
	   * 这里可以做任何针对异常的处理,比如记录日志等等
	   */
	  public void uncaughtException(Thread a, Throwable e) {
	    System.out.println("This is:" + a.getName() + ",Message:"
	        + e.getMessage());
	    e.printStackTrace();
	  }
	}