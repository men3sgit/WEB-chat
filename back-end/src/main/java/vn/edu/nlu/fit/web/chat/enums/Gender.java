package vn.edu.nlu.fit.web.chat.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum representing different gender options.
 */
public enum Gender {

    @JsonProperty("male")
    MALE,

    @JsonProperty("female")
    FEMALE,

    @JsonProperty("other")
    OTHER;

    /**
     * No-argument constructor.
     */
    Gender() {
        // Optional: Add initialization logic if needed
    }
}
