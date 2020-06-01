package org.seventwentyseventechnologies.haveibeenpwned.api.v3.client;

import org.seventwentyseventechnologies.haveibeenpwned.configuration.APIConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class APIClientTest {

    @Test(groups = "integration")
    public void shouldBeSuccessfulWhenGetAllBreaches() {
        var configuration = new APIConfiguration();
        var client = new APIClient(configuration);
        var breaches = client.getBreaches();
        assertNotNull(breaches);
        assertNotNull(breaches.getBreaches());
        assertTrue(breaches.getBreaches().size() > 0);
    }

    @Test(groups = "integration")
    public void shouldBeSuccessfulWhenGetAllBreachesWithFiltering() {
        var configuration = new APIConfiguration();
        var client = new APIClient(configuration);
        var breaches = client.getBreaches("adobe.com");
        assertNotNull(breaches);
        assertNotNull(breaches.getBreaches());
        assertEquals(breaches.getBreaches().size(), 1);
    }
}