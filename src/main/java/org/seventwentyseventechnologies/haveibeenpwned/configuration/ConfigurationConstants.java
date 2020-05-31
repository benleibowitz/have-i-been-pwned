package org.seventwentyseventechnologies.haveibeenpwned.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigurationConstants {
    PROPERTIES_FILE("haveibeenpwnedclient.properties");

    private final String value;
}
