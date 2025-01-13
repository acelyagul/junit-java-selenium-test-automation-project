package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    public static void info(String message) {
        try {
            logger.info(message);
            System.out.println("INFO: " + message); 
        } catch (Exception e) {
            System.err.println("Logging Error: " + e.getMessage());
        }
    }

    public static void error(String message) {
        try {
            logger.error(message);
            System.err.println("ERROR: " + message); 
        } catch (Exception e) {
            System.err.println("Logging Error: " + e.getMessage());
        }
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
} 