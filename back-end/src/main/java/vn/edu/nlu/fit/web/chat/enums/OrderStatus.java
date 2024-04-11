package vn.edu.nlu.fit.web.chat.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {
    @JsonProperty("pending")
    PENDING,
    @JsonProperty("processing")
    PROCESSING,
    @JsonProperty("completed")
    COMPLETED,
    @JsonProperty("canceled")
    CANCELED
}
