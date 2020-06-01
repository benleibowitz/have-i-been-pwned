package org.seventwentyseventechnologies.haveibeenpwned.api.v3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * V3 API object for Breaches
 */
@Data
@AllArgsConstructor
public class Breaches {
    /**
     * Array of {@link Breach}
     */
    private Breach[] breaches;
}
