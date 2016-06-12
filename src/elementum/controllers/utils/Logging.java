package elementum.controllers.utils;

import java.util.logging.*;

/**
 * @author Alexander Elbracht
 */
public class Logging {
    private Logger logger;

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

    public void log(Level level, String message) {
        logger.log(level, message);
    }

    public void log(Level level, String message, Exception ex) {
        logger.log(level, message, ex);
    }
}
