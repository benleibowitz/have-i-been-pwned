package org.seventwentyseventechnologies.haveibeenpwned.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;
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
     * Some APIs requires authorization
     */
    @Getter(AccessLevel.NONE)
    private final String apiKey;

    /**
     * API version string
     */
    private final String apiVersion;

    /**
     * Constructor without API key - some API endpoints do not require authorization.
     *
     * @see <a href="https://haveibeenpwned.com/API/v3#Authorisation">API Documentation for Authorization</a>
     */
    public APIConfiguration() {
        this(null);
    }

    /**
     * Constructor with API key.
     *
     * @param apiKey api key
     * @see <a href="https://haveibeenpwned.com/API/v3#Authorisation">API Documentation for Authorization</a>
     */
    public APIConfiguration(final String apiKey) {
        var properties = loadProperties();
        this.apiKey = apiKey;
        this.userAgent = properties.getProperty(ConfigurationProperties.USER_AGENT.getPropertyKey());
        this.apiVersion = properties.getProperty(ConfigurationProperties.API_VERSION.getPropertyKey());
    }

    /**
     * Get API key as a nullable Optional
     *
     * @return optional
     */
    public Optional<String> getApiKey() {
        return Optional.ofNullable(apiKey);
    }

    /**
     * Load library config properties from file
     *
     * @return properties
     */
    @SneakyThrows
    private Properties loadProperties() {
        try (var propStream = getClass().getClassLoader().getResourceAsStream(ConfigurationProperties.PROPERTIES_FILE)) {
            var properties = new Properties();
            properties.load(propStream);
            return properties;
        }
    }
}
