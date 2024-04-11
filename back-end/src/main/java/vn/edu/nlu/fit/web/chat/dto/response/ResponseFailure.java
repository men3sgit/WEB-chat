package vn.edu.nlu.fit.web.chat.dto.response;

import org.springframework.http.HttpStatus;

/**
 * Represents a failure response with an HTTP status code and a message.
 * Inherits from ResponseSuccess, as failure responses can use the same structure as success responses.
 */
public class ResponseFailure extends ResponseSuccess {

    /**
     * Constructs a ResponseFailure object with the given HTTP status and message.
     *
     * @param status  The HTTP status code indicating the failure. Must not be null.
     *                Examples: {@link org.springframework.http.HttpStatus#BAD_REQUEST},
     *                {@link org.springframework.http.HttpStatus#NOT_FOUND},
     *                {@link org.springframework.http.HttpStatus#FORBIDDEN}.
     * @param message A descriptive message explaining the failure. Must not be null or empty.
     */
    public ResponseFailure(HttpStatus status, String message) {
        super(status, message);
    }
    public ResponseFailure(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
