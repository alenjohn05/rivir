package com.spring_basic_auth.basic_auth.controller;

import com.spring_basic_auth.basic_auth.utils.ConfigCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
    private final ConfigCheck configCheck;
    private final DataSource dataSource;

    @Autowired
    public HealthCheckController(ConfigCheck configCheck, DataSource dataSource) {
        this.configCheck = configCheck;
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> healthStatus = new HashMap<>();

        // Database connectivity check
        boolean isDbConnected = checkDatabase();
        healthStatus.put("Database", isDbConnected ? "Connected" : "Disconnected");

        // External API availability check (example using a fictional API)
        boolean isExternalApiAvailable = checkExternalApi();
        healthStatus.put("External API", isExternalApiAvailable ? "Available" : "Not available");

        // Resource utilization check (example of checking CPU usage)
        double cpuUsage = checkCpuUsage();
        healthStatus.put("CPU Usage", cpuUsage + "%");

        // Configuration checks
        configCheck.performConfigChecks();

        boolean loggingAndMonitoringStatus = checkLoggingAndMonitoring();
        healthStatus.put("Logging and Monitoring", loggingAndMonitoringStatus ? "Available" : "Not available");

        return ResponseEntity.ok(healthStatus);
    }

    private boolean checkDatabase() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if (connection.isValid(1)) {
                logger.info("Database connection successful");
                return true; // Connection successful
            } else {
                logger.warn("Database connection validation failed");
                return false; // Connection validation failed
            }
        } catch (SQLException e) {
            logger.error("Error connecting to the database: {}", e.getMessage());
            return false; // Connection failed
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing database connection: {}", e.getMessage());
                }
            }
        }
    }

    // Simulated method for checking external API availability
    private boolean checkExternalApi() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://www.google.com");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                logger.info("External API is available: HTTP_OK");
                return true; // API responds successfully
            } else {
                logger.warn("External API responded with HTTP code: {}", responseCode);
                return false; // API responded with an unexpected status code
            }
        } catch (IOException e) {
            logger.error("Error while checking external API: {}", e.getMessage());
            return false; // Exception occurred while making the request
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Simulated method for checking CPU usage (example)
    private double checkCpuUsage() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] loadTicks = processor.getSystemCpuLoadTicks();

        // Calculate CPU load percentage
        double cpuLoadPercentage = calculateCPULoadPercentage(loadTicks);

        logger.info("CPU Usage: {}%", cpuLoadPercentage);
        return cpuLoadPercentage;
    }

    private double calculateCPULoadPercentage(long[] loadTicks) {
        double systemLoad = 0d;
        for (long tick : loadTicks) {
            systemLoad += tick;
        }
        double cpuLoad = systemLoad / (loadTicks.length * 100d);
        return cpuLoad * 100;
    }

    private boolean checkLoggingAndMonitoring() {
        // Simulate a basic check by logging a test message and verifying its presence in the logs
        try {
            logger.info("Testing logging and monitoring: log message generated");
            // Ideally, here you would verify if the log message has been captured by the logging system
            return true; // Log message created successfully
        } catch (Exception e) {
            logger.error("Error testing logging and monitoring: {}", e.getMessage());
            return false; // Log message creation failed
        }
    }


}
