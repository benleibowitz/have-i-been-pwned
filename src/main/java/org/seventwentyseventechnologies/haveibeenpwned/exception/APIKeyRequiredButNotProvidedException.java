package org.seventwentyseventechnologies.haveibeenpwned.exception;

public class APIKeyRequiredButNotProvidedException extends Exception {
    public APIKeyRequiredButNotProvidedException(String message) {
        super(message);
    }
}
