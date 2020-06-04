package org.seventwentyseventechnologies.haveibeenpwned.api.v3.client;

import org.seventwentyseventechnologies.haveibeenpwned.configuration.APIConfiguration;
import org.seventwentyseventechnologies.haveibeenpwned.exception.APIKeyRequiredButNotProvidedException;
import org.testng.annotations.Test;

import javax.ws.rs.NotAuthorizedException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Integration test for {@link APIClient}
 */
public class APIClientTest {
    /**
     * Test that the Get All Breaches method returns success with no filtering.
     * {@link APIClient#getBreaches()}
     */
    @Test(groups = "integration")
    public void shouldBeSuccessfulWhenGetAllBreaches() {
        var configuration = new APIConfiguration();
        var client = new APIClient(configuration);
        var breaches = client.getBreaches();
        assertNotNull(breaches);
        assertNotNull(breaches.getBreaches());
        assertTrue(breaches.getBreaches().size() > 0);
    }

    /**
     * Test that the Get All Breaches method returns success with filtering applied by domain
     * {@link APIClient#getBreaches(String)}
     */
    @Test(groups = "integration")
    public void shouldBeSuccessfulWhenGetAllBreachesWithFiltering() {
        var configuration = new APIConfiguration();
        var client = new APIClient(configuration);
        var breaches = client.getBreaches("adobe.com");
        assertNotNull(breaches);
        assertNotNull(breaches.getBreaches());
        assertEquals(breaches.getBreaches().size(), 1);
    }

    /**
     * Test that the Get Breaches For Account method returns a failure
     * {@link APIClient#getBreachesForAccount(String)}
     *
     * @throws APIKeyRequiredButNotProvidedException exception
     */
    @Test(groups = "integration", expectedExceptions = NotAuthorizedException.class)
    public void shouldFailWhenGetBreachesForAccountWithInvalidAPIKeyProvided() throws APIKeyRequiredButNotProvidedException {
        var configuration = new APIConfiguration("invalid-integration-test");
        var client = new APIClient(configuration);
        client.getBreachesForAccount("foo@bar.com");
    }
}