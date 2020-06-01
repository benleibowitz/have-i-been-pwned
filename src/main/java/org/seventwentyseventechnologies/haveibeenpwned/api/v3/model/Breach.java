package org.seventwentyseventechnologies.haveibeenpwned.api.v3.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDate;

/**
 * V3 Breach object for haveibeenpwned API
 *
 * @see <a href="https://haveibeenpwned.com/API/v3#BreachModel">API Documentation for Breach Model</a>
 */
@Accessors(chain = true)
@Getter
@Setter
public class Breach {
    /**
     *  A Pascal-cased name representing the breach which is unique across all other breaches.
     *  This value never changes and may be used to name dependent assets (such as images) but
     *  should not be shown directly to end users (see the "Title" attribute instead).
     */
    @SerializedName("Name")
    private String name;
    /**
     * A descriptive title for the breach suitable for displaying to end users.
     * It's unique across all breaches but individual values may change in the future
     * (i.e. if another breach occurs against an organisation already in the system).
     * If a stable value is required to reference the breach, refer to the "Name" attribute instead.
     */
    @SerializedName("Title")
    private String title;
    /**
     * The domain of the primary website the breach occurred on.
     * This may be used for identifying other assets external systems may have for the site.
     */
    @SerializedName("Domain")
    private String domain;
    /**
     * The date (with no time) the breach originally occurred on in ISO 8601 format. This is not always accurate —
     * frequently breaches are discovered and reported long after the original incident. Use this attribute as a guide only.
     */
    @SerializedName("BreachDate")
    private LocalDate breachDate;
    /**
     * The date and time (precision to the minute) the breach was added to the system in ISO 8601 format.
     */
    @SerializedName("AddedDate")
    private Instant addedDate;
    /**
     * The date and time (precision to the minute) the breach was modified in ISO 8601 format.
     * This will only differ from the AddedDate attribute if other attributes represented here
     * are changed or data in the breach itself is changed (i.e. additional data is identified and loaded).
     * It is always either equal to or greater then the AddedDate attribute, never less than.
     */
    @SerializedName("ModifiedDate")
    private Instant modifiedDate;
    /**
     * The total number of accounts loaded into the system.
     * This is usually less than the total number reported by the media due
     * to duplication or other data integrity issues in the source data.
     */
    @SerializedName("PwnCount")
    private Integer pwnCount;
    /**
     * Contains an overview of the breach represented in HTML markup.
     * The description may include markup such as emphasis and strong tags as well as hyperlinks.
     */
    @SerializedName("Description")
    private String description;
    /**
     * This attribute describes the nature of the data compromised
     * in the breach and contains an alphabetically ordered string array of impacted data classes.
     */
    @SerializedName("DataClasses")
    private String[] dataClasses;
    /**
     * Indicates that the breach is considered unverified. An unverified breach may not have been hacked from the indicated website.
     * An unverified breach is still loaded into HIBP when there's sufficient confidence that a significant portion of the data is legitimate.
     */
    @SerializedName("IsVerified")
    private boolean isVerified;
    /**
     * Indicates that the breach is considered fabricated. A fabricated breach is unlikely to have been hacked
     * from the indicated website and usually contains a large amount of manufactured data.
     * However, it still contains legitimate email addresses and asserts that the account owners
     * were compromised in the alleged breach.
     */
    @SerializedName("IsFabricated")
    private boolean isFabricated;
    /**
     * Indicates if the breach is considered sensitive.
     * The public API will not return any accounts for a breach flagged as sensitive.
     */
    @SerializedName("IsSensitive")
    private boolean isSensitive;
    /**
     * Indicates if the breach has been retired. This data has been permanently removed and will not be returned by the API.
     */
    @SerializedName("IsRetired")
    private boolean isRetired;
    /**
     * Indicates if the breach is considered a spam list.
     * This flag has no impact on any other attributes but it means that the data has not come as a result of a security compromise.
     */
    @SerializedName("IsSpamList")
    private boolean isSpamList;
    /**
     * A URI that specifies where a logo for the breached service can be found. Logos are always in PNG format.
     */
    @SerializedName("LogoPath")
    private String logoPath;
}
