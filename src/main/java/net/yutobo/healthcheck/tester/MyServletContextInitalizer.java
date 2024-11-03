package net.yutobo.healthcheck.tester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Component
public class MyServletContextInitalizer implements ServletContextInitializer {
    private static final Logger logger = LoggerFactory.getLogger(MyServletContextInitalizer.class);
    private static final String DEFAULT_ALLOWLIST_FILE = "C:\\home\\allowlist.txt";
    private static final long DEFAULT_DELAY_MS = 120*1000L;
    private static final long DELAY_MS = getDelayMS();

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (shouldSkipDelay()) {
            return;
        }

        logger.info("Startup delay: {} s", DELAY_MS / 1000);

        try {
            Thread.sleep(getDelayMS());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Slept in initializer.");
    }

    private static long getDelayMS() {
        var delayMs = System.getenv("STARTUP_DELAY_MS");

        if (delayMs == null) {
            return DEFAULT_DELAY_MS;
        }
        
        try {
            return Long.parseLong(delayMs);
        } catch (NumberFormatException e) {
            return DEFAULT_DELAY_MS;
        }
    }

    private boolean shouldSkipDelay() {
        String allowListFile = System.getenv("ALLOWLIST_FILE");
        if (allowListFile == null) {
            allowListFile = DEFAULT_ALLOWLIST_FILE;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(allowListFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(System.getenv("COMPUTERNAME"))) {
                    logger.info("{} found in allowlist.", System.getenv("COMPUTERNAME"));
                    return true;
                }
            }
        } catch (IOException ex) {
            logger.warn("file not found...", ex);
            return false;
        }

        return false;
    }
}