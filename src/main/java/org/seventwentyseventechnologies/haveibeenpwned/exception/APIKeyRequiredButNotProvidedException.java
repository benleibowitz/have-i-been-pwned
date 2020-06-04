package org.seventwentyseventechnologies.haveibeenpwned.exception;

/**
 * Thrown when calling APIs that require an API key, but one has not been provided.
 * You can provide one by setting it in the {@link org.seventwentyseventechnologies.haveibeenpwned.configuration.APIConfiguration#APIConfiguration(String)}
 */
public class APIKeyRequiredButNotProvidedException extends Exception {
    /**
     * Constructor
     */
    public APIKeyRequiredButNotProvidedException() {
        super("API key was required for this API, but not provided. Please set it in the APIConfiguration.");
    }
}
