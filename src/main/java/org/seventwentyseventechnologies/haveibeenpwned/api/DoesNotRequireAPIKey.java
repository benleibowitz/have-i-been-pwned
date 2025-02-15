package org.seventwentyseventechnologies.haveibeenpwned.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that this API does not require an API key. An FYI.
 */
@Retention(value = RetentionPolicy.SOURCE)
@Target(value = ElementType.METHOD)
public @interface DoesNotRequireAPIKey {
}
