package org.seventwentyseventechnologies.haveibeenpwned.api.v3.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breach;
import org.seventwentyseventechnologies.haveibeenpwned.api.v3.model.Breaches;

/**
 * Transform versioned {@link Breaches} to canonical {@link org.seventwentyseventechnologies.haveibeenpwned.model.Breaches}
 */
@Mapper
public interface APIToCanonicalModelTransformer {
    /**
     * Instance
     */
    APIToCanonicalModelTransformer INSTANCE = Mappers.getMapper(APIToCanonicalModelTransformer.class);

    /**
     * Transform V3 {@link Breaches} to canonical {@link org.seventwentyseventechnologies.haveibeenpwned.model.Breaches}
     *
     * @param api to transform
     * @return model
     */
    org.seventwentyseventechnologies.haveibeenpwned.model.Breaches fromAPI(final Breaches api);

    /**
     * Transform V3 {@link Breach} to canonical {@link org.seventwentyseventechnologies.haveibeenpwned.model.Breach}
     *
     * @param api to transform
     * @return model
     */
    @Mapping(target = "numberAccountsAffected", source = "pwnCount")
    @Mapping(target = "dataClassesExposed", source = "dataClasses")
    org.seventwentyseventechnologies.haveibeenpwned.model.Breach fromAPI(final Breach api);
}
