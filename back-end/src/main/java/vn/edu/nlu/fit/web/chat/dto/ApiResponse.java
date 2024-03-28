package vn.edu.nlu.fit.web.chat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

import static vn.edu.nlu.fit.web.chat.utils.HttpRequestUtil.getHttpRequestURL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields in JSON response
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
public class ApiResponse<T> {

    @JsonProperty("status_code") // Customize property name for clarity in JSON response
    private int statusCode;

    private String message;

    @JsonProperty("data") // Explicitly name property for clarity
    private T data;

    @JsonProperty("timestamp") // Descriptive property name
    private Instant timestamp;

    private String url;

    // Prefer Optional for success responses with no data
    public ApiResponse(T data) {
        this.data = data;
        this.statusCode = HttpStatus.OK.value();
        this.message = "Success";
        this.timestamp = Instant.now();
        this.url = getHttpRequestURL();
    }

    // Separate constructor for error responses
    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = Instant.now();
        this.url = getHttpRequestURL();
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                ", url='" + url + '\'' +
                '}';
    }
}
