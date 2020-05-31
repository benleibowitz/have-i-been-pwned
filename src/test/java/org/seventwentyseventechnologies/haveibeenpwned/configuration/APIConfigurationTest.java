package org.seventwentyseventechnologies.haveibeenpwned.configuration;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Integration test {@link APIConfiguration}
 */
public class APIConfigurationTest {
    /**
     * Test that the properties are loaded into the {@link APIConfiguration}
     */
    @Test(groups = "integration")
    public void clientConfigurationShouldLoadProperties() {
        APIConfiguration configuration = new APIConfiguration("test-api-key");

        assertEquals(configuration.getApiKey(), "test-api-key");
        String userAgent = configuration.getUserAgent();
        assertNotNull(userAgent);
        assertTrue(userAgent.matches(".*have-i-been-pwned Java Client.*org.seventwentyseventechnologies.haveibeenpwned:have-i-been-pwned-client:[0-9.]+.*"));
    }
}