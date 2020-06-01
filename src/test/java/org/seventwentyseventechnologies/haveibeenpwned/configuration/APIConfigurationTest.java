package org.seventwentyseventechnologies.haveibeenpwned.configuration;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Integration and unit tests for {@link APIConfiguration}
 */
public class APIConfigurationTest {
    /**
     * Test that the properties are loaded into the {@link APIConfiguration}
     */
    @Test(groups = "integration")
    public void shouldLoadProperties() {
        var configuration = new APIConfiguration("test-api-key");

        assertEquals(configuration.getApiKey(), "test-api-key");
        assertEquals(configuration.getApiVersion(), "v3");
        assertEquals(configuration.getUserAgent(), "have-i-been-pwned Java Client: org.seventwentyseventechnologies.haveibeenpwned:have-i-been-pwned-client");
    }
}