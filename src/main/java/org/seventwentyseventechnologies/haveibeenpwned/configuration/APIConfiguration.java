package org.seventwentyseventechnologies.haveibeenpwned.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Properties;

/**
 * API configuration class
 */
@Getter
@RequiredArgsConstructor
public class APIConfiguration {
    /**
     * The API requests a user-agent to identify the calling app
     *
     * @see <a href="https://haveibeenpwned.com/API/v3#UserAgent">haveibeenpwned API Documentation for user agent</a>
     */
    private final String userAgent;

    /**
     * The API requires a key
     */
    private final String apiKey;

    public APIConfiguration(final String apiKey) {
        var properties = loadProperties();
        this.apiKey = apiKey;
        this.userAgent = properties.getProperty("user-agent");
    }

    @SneakyThrows
    private Properties loadProperties() {
        try (var propStream = getClass().getClassLoader().getResourceAsStream(ConfigurationConstants.PROPERTIES_FILE.getValue())) {
            var properties = new Properties();
            properties.load(propStream);
            return properties;
        }
    }
}
