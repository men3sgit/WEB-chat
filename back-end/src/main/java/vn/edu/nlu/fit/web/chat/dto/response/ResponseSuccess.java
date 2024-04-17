package vn.edu.nlu.fit.web.chat.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * Represents a successful response with an HTTP status code, a message, and optional data.
 * Extends ResponseEntity to provide compatibility with Spring MVC.
 */
public class ResponseSuccess<T> extends ResponseEntity<ResponseSuccess.Payload<T>> {

    /**
     * Constructs a ResponseSuccess object with the given HTTP status and message.
     *
     * @param status  The HTTP status code indicating the success. Must not be null.
     * @param message A descriptive message explaining the success. Must not be null.
     */
    public ResponseSuccess(HttpStatus status, String message) {
        super(new Payload(status.value(), message), HttpStatus.OK);
    }

    /**
     * Constructs a ResponseSuccess object with the given HTTP status, message, and data.
     *
     * @param status  The HTTP status code indicating the success. Must not be null.
     * @param message A descriptive message explaining the success. Must not be null.
     * @param data    Optional data to be included in the response. Can be null.
     */
    public ResponseSuccess(HttpStatus status, String message, T data) {
        super(new Payload(status.value(), message, data), status);
    }

    /**
     * Constructs a ResponseSuccess object with the given payload and HTTP status.
     *
     * @param body   The payload containing status, message, and optional data.
     * @param status The HTTP status code indicating the success. Must not be null.
     */
    public ResponseSuccess(Payload body, HttpStatus status) {
        super(body, status);
    }

    /**
     * Constructs a ResponseSuccess object with the given headers and HTTP status.
     *
     * @param headers The headers to be included in the response.
     * @param status  The HTTP status code indicating the success. Must not be null.
     */
    public ResponseSuccess(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    /**
     * Constructs a ResponseSuccess object with the given payload, headers, and raw HTTP status.
     *
     * @param payload   The payload containing status, message, and optional data.
     * @param headers   The headers to be included in the response.
     * @param rawStatus The raw HTTP status code indicating the success.
     */
    public ResponseSuccess(Payload payload, MultiValueMap<String, String> headers, int rawStatus) {
        super(payload, headers, rawStatus);
    }

    /**
     * Constructs a ResponseSuccess object with the given payload, headers, and HTTP status.
     *
     * @param payload The payload containing status, message, and optional data.
     * @param headers The headers to be included in the response.
     * @param status  The HTTP status code indicating the success. Must not be null.
     */
    public ResponseSuccess(Payload payload, MultiValueMap<String, String> headers, HttpStatus status) {
        super(payload, headers, status);
    }

    /**
     * Represents the payload of a successful response.
     */
    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class Payload<T> {
        private final int status;
        private final String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private T data;


    }
}
