package org.seventwentyseventechnologies.haveibeenpwned.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Configuration file properties
 */
@Getter
@RequiredArgsConstructor
public enum ConfigurationProperties {
    /**
     * User agent key
     */
    USER_AGENT("api.header.user-agent"),
    /**
     * API version key
     */
    API_VERSION("api.version");
    /**
     * File name
     */
    public static final String PROPERTIES_FILE = "haveibeenpwnedclient.properties";
    /**
     * Key in the properties file
     */
    private final String propertyKey;

}
