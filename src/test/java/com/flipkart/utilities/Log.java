package com.flipkart.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log 
{
	private Log() // for preventing instantiation - enforces static usage
	{}

	 // Classic way - passing class name
    public static Logger getLogger(Class<?> clazz) 
    {
        return LogManager.getLogger(clazz);
    }

    // Auto-detecting class 
    public static Logger getLogger() 
    {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        return LogManager.getLogger(className);
    }
	 
	public static final Logger logger = LogManager.getLogger(Log.class);

	public static void info(String message) {
		logger.info(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}

	public static void error(String message) {
		logger.error(message);
	}

	public static void fatal(String message) {
		logger.fatal(message);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

}
