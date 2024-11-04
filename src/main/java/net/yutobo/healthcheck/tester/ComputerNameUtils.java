package net.yutobo.healthcheck.tester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerNameUtils {
    private static final Logger logger = LoggerFactory.getLogger(ComputerNameUtils.class);

    private static final String DEFAULT_DELAYLIST_FILE = "C:\\home\\delaylist.txt";
    public static final String DELAYLIST_FILE = System.getenv("DELAYLIST_FILE") == null ? DEFAULT_DELAYLIST_FILE
            : System.getenv("DELAYLIST_FILE");
    
    private static final String DEFAULT_UNHEALTYLIST_FILE = "C:\\home\\unhealthylist.txt";
    public static final String UNHEALTHYLIST_FILE = System.getenv("UNHEALTHYLIST_FILE") == null ? DEFAULT_UNHEALTYLIST_FILE
    : System.getenv("UNHEALTHYLIST_FILE");

    private ComputerNameUtils() {
    }

    public static boolean hasComputerName(String pathToListFile) {
        return getComputerList(pathToListFile).stream().anyMatch(computerName -> {
            if (computerName.equals(System.getenv("COMPUTERNAME"))) {
                logger.info("{} found in the list.", System.getenv("COMPUTERNAME"));
                return true;
            }
            return false;
        });
    }

    public static List<String> getComputerList(String pathToListFile) {
        List<String> computerList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToListFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                computerList.add(line);
            }
        } catch (IOException ex) {
            logger.warn("file not found...", ex);
        }

        return computerList;
    }
}
