package org.seventwentyseventechnologies.haveibeenpwned.api.v3.transformer;

import com.github.javafaker.Faker;
import org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breach;
import org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Unit test {@link APIToCanonicalModelTransformer}
 */
public class APIToCanonicalModelTransformerTest {
    /**
     * For random data generation
     */
    private static final Faker FAKER = new Faker();

    @Test(groups = "unit")
    public void shouldTransformBreachesAPIObjectToCanonicalModel() {
        var apiBreach1 = buildBreach();
        var apiBreach2 = buildBreach();
        var apiBreaches = new Breaches(new Breach[]{apiBreach1, apiBreach2});

        var modelBreaches = APIToCanonicalModelTransformer.INSTANCE.fromAPI(apiBreaches);
        assertNotNull(modelBreaches);
        assertEquals(modelBreaches.getBreaches().size(), apiBreaches.getBreaches().length);
        for (int i = 0; i < apiBreaches.getBreaches().length; i++) {
            var apiBreach = apiBreaches.getBreaches()[i];
            var modelBreach = modelBreaches.getBreaches().get(i);

            assertEquals(modelBreach.getName(), apiBreach.getName());
            assertEquals(modelBreach.getTitle(), apiBreach.getTitle());
            assertEquals(modelBreach.getDomain(), apiBreach.getDomain());
            assertEquals(modelBreach.getBreachDate(), apiBreach.getBreachDate());
            assertEquals(modelBreach.getAddedDate(), apiBreach.getAddedDate());
            assertEquals(modelBreach.getModifiedDate(), apiBreach.getModifiedDate());
            assertEquals(modelBreach.getNumberAccountsAffected(), apiBreach.getPwnCount());
            assertEquals(modelBreach.getDescription(), apiBreach.getDescription());
            assertNotNull(modelBreach.getDataClassesExposed());
            assertEquals(modelBreach.getDataClassesExposed().size(), apiBreach.getDataClasses().length);
            assertEquals(modelBreach.isVerified(), apiBreach.isVerified());
            assertEquals(modelBreach.isFabricated(), apiBreach.isFabricated());
            assertEquals(modelBreach.isSensitive(), apiBreach.isSensitive());
            assertEquals(modelBreach.isRetired(), apiBreach.isRetired());
            assertEquals(modelBreach.isSpamList(), apiBreach.isSpamList());
        }
    }

    private Breach buildBreach() {
        var company = FAKER.company();
        return new Breach()
                .setName(company.name() + " name")
                .setTitle(company.name() + " title")
                .setDomain(company.url())
                .setBreachDate(LocalDate.from(randomInstant().atZone(ZoneOffset.UTC)))
                .setAddedDate(randomInstant())
                .setModifiedDate(randomInstant())
                .setPwnCount(FAKER.number().randomDigitNotZero())
                .setDescription(FAKER.lorem().characters())
                .setDataClasses(new String[]{FAKER.lorem().characters(), FAKER.lorem().characters()})
                .setFabricated(FAKER.bool().bool())
                .setVerified(FAKER.bool().bool())
                .setRetired(FAKER.bool().bool())
                .setSensitive(FAKER.bool().bool())
                .setLogoPath(FAKER.lorem().characters());

    }

    /**
     * Random {@link Instant}
     *
     * @return Instant
     */
    private Instant randomInstant() {
        return FAKER.date().past(1000, TimeUnit.DAYS).toInstant();
    }
}