package com.spring_basic_auth.basic_auth.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConfigCheck {

    private static final Logger logger = LoggerFactory.getLogger(ConfigCheck.class);

    private final Environment environment;

    @Value("${custom.property}")
    private String customProperty;

    public ConfigCheck(Environment environment) {
        this.environment = environment;
    }

    public void performConfigChecks() {
        logger.info("Performing configuration checks...");

        // Check for required properties
        String[] requiredProperties = {"spring.datasource.url", "custom.property"};
        Arrays.stream(requiredProperties)
                .filter(property -> environment.getProperty(property) != null)
                .forEach(property -> logger.info("Property '{}' is properly loaded.", property));

        // Validate specific properties or conditions
        if (customProperty != null && !customProperty.isBlank()) {
            logger.info("Custom property '{}' is valid.", customProperty);
        } else {
            logger.warn("Custom property is either missing or empty.");
        }

        logger.info("Configuration checks completed.");
    }
}
