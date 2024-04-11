package vn.edu.nlu.fit.web.chat.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Priority {
    @JsonProperty("low")
    LOW,
    @JsonProperty("medium")
    MEDIUM,
    @JsonProperty("high")
    HIGH
}
