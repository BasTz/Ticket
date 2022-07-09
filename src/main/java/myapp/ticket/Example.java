package myapp.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {
    private static final Logger logger = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        logger.warn("Example log from BasTz! warn");
        logger.debug("Example log from BasTz! debug");
        logger.error("Example log from BasTz! error");
        logger.info("Example log from BasTz! info");
    }
}
