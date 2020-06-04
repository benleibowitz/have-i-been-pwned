package org.seventwentyseventechnologies.haveibeenpwned.api.v3.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.seventwentyseventechnologies.haveibeenpwned.api.DoesNotRequireAPIKey;
import org.seventwentyseventechnologies.haveibeenpwned.api.v3.transformer.APIToCanonicalModelTransformer;
import org.seventwentyseventechnologies.haveibeenpwned.api.v3.transformer.JAXRSExceptionProvider;
import org.seventwentyseventechnologies.haveibeenpwned.configuration.APIConfiguration;
import org.seventwentyseventechnologies.haveibeenpwned.exception.APIKeyRequiredButNotProvidedException;
import org.seventwentyseventechnologies.haveibeenpwned.model.Breach;
import org.seventwentyseventechnologies.haveibeenpwned.model.Breaches;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDate;

import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static javax.ws.rs.core.Response.Status.Family.familyOf;

/**
 * API client for haveibeenpwned
 */
@RequiredArgsConstructor
public class APIClient {
    /**
     * The base URL for haveibeenpwned
     */
    private static final String BASE_URL = "https://haveibeenpwned.com/api/";
    /**
     * We will pass the user-agent to the API to identify ourselves
     */
    private static final String USER_AGENT_HEADER_KEY = "user-agent";
    /**
     * The API key header key
     */
    private static final String API_KEY_HEADER_KEY = "hibp-api-key";
    /**
     * A domain query param filter
     */
    private static final String DOMAIN_QUERY_PARAM_KEY = "domain";
    /**
     * GSON object for deserializing the response
     */
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, ctx) -> LocalDate.parse(json.getAsString()))
            .registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) (json, type, ctx) -> Instant.parse(json.getAsString()))
            .registerTypeAdapter(org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches.class,
                    (JsonDeserializer<Object>) (json, type, ctx) ->
                            new org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches(ctx.deserialize(json,
                                    org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breach[].class)))
            .create();
    /**
     * Transformer for transforming the haveibeenpwned versioned API object {@link org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches}
     * to canonical {@link Breaches}
     */
    private final APIToCanonicalModelTransformer transformer = APIToCanonicalModelTransformer.INSTANCE;

    /**
     * JAX-RS web application exception
     */
    private final JAXRSExceptionProvider jaxrsExceptionProvider = new JAXRSExceptionProvider();

    /**
     * API configuration
     */
    private final APIConfiguration configuration;

    /**
     * Get all {@link Breach}es with no filtering.
     * <p>
     * This is an unauthorized API and does not require an API key
     *
     * @return breaches
     * @see <a href="https://haveibeenpwned.com/API/v3#AllBreaches">API Documentation - All Breaches API</a>
     */
    @SneakyThrows
    @DoesNotRequireAPIKey
    public Breaches getBreaches() {
        return getBreaches(null);
    }

    /**
     * Get all breaches with filtering
     * <p>
     * This is an unauthorized API and does not require an API key
     *
     * @param domainFilter a domain name filter to pass to the API as a query parameter
     * @return breaches
     * @see <a href="https://haveibeenpwned.com/API/v3#AllBreaches">API Documentation - All Breaches API</a>
     */
    @SneakyThrows
    @DoesNotRequireAPIKey
    public Breaches getBreaches(final String domainFilter) {
        var webTarget = ClientBuilder.newClient().target(BASE_URL + configuration.getApiVersion() + "/breaches");
        try (var response = webTarget
                .queryParam(DOMAIN_QUERY_PARAM_KEY, domainFilter)
                .request(MediaType.APPLICATION_JSON)
                .header(USER_AGENT_HEADER_KEY, configuration.getUserAgent())
                .get()) {
            var rawJson = response.readEntity(String.class);
            var apiBreaches = GSON.fromJson(rawJson, org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches.class);
            return transformer.fromAPI(apiBreaches);
        }
    }

    /**
     * Get all the {@link Breach}es for this account.
     *
     * <strong>This is an authorized account, and requires an API key passed with the
     * {@link APIConfiguration} in {@link APIClient#APIClient(APIConfiguration)}</strong>.
     *
     * @param account account
     * @return Breaches for that account
     * @throws APIKeyRequiredButNotProvidedException if API key not provided to {@link APIConfiguration} during construction
     * @see <a href="https://haveibeenpwned.com/API/v3#BreachesForAccount">API Documentation for Breaches Per Account Endpoint</a>
     */
    public Breaches getBreachesForAccount(final String account) throws APIKeyRequiredButNotProvidedException {
        var webTarget = ClientBuilder.newClient().target(BASE_URL + configuration.getApiVersion() + "/breachedaccount/{id}");
        try (var response = webTarget
                .resolveTemplate("id", account)
                .queryParam("truncateResponse", false)
                .request(MediaType.APPLICATION_JSON)
                .header(USER_AGENT_HEADER_KEY, configuration.getUserAgent())
                .header(API_KEY_HEADER_KEY, configuration.getApiKey().orElseThrow(APIKeyRequiredButNotProvidedException::new))
                .get()) {

            var rawJson = response.readEntity(String.class);

            if (familyOf(response.getStatus()) != SUCCESSFUL) {
                throw jaxrsExceptionProvider.getException(response);
            }

            var apiBreaches = GSON.fromJson(rawJson, org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches.class);
            return transformer.fromAPI(apiBreaches);
        }
    }
}
