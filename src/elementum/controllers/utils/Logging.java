package elementum.controllers.utils;

import java.util.logging.*;

/**
 * @author Alexander Elbracht
 *
 * This class log messages in console and in log file
 */
public class Logging {
    private Logger logger;

    /**
     * Constructor
     */
    public Logging() {
        logger = Logger.getLogger(getClass().getName());

        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("./guardians-of-nature.log");

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
        }
        catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception", ex);
        }
    }

    /**
     * Log a message
     * @param level Log level
     * @param message Message
     */
    public void log(Level level, String message) {
        logger.log(level, message);
    }

    /**
     * Log a exception
     * @param level Log level
     * @param message Message
     * @param ex Exception
     */
    public void log(Level level, String message, Exception ex) {
        logger.log(level, message, ex);
    }
}
