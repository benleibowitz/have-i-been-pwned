package org.seventwentyseventechnologies.haveibeenpwned.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Breaches {
    private List<Breach> breaches;
}
