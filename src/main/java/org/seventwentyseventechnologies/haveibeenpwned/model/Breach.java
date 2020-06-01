package org.seventwentyseventechnologies.haveibeenpwned.model;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
public class Breach {
    private String name;
    private String title;
    private String domain;
    private Integer numberAccountsAffected;
    private LocalDate breachDate;
    private Instant addedDate;
    private Instant modifiedDate;
    private String description;
    private List<String> dataClassesExposed;
    private boolean isVerified;
    private boolean isFabricated;
    private boolean isSensitive;
    private boolean isRetired;
    private boolean isSpamList;
    private String logoPath;
}
