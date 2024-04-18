package vn.edu.nlu.fit.web.chat.dto.response;

import org.springframework.http.HttpStatus;

/**
 * Represents an error response with an HTTP status code and a message.
 * Inherits from ResponseData, which provides the basic structure for response data.
 */
public class ResponseError extends ResponseFailure {

    /**
     * Constructs a ResponseError object with the given HTTP status and message.
     *
     * @param status  The HTTP status code indicating the error. Must not be null.
     *                Examples:
     *                - {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR} (500)
     *                - Indicates that an unexpected condition was encountered on the server.
     *                - {@link org.springframework.http.HttpStatus#SERVICE_UNAVAILABLE} (503)
     *                - Indicates that the server is currently unable to handle the request due to temporary overloading or maintenance of the server.
     *                - {@link org.springframework.http.HttpStatus#GATEWAY_TIMEOUT} (504)
     *                - Indicates that the server, while acting as a gateway or proxy, did not receive a timely response from the upstream server it needed to access in order to complete the request.
     * @param message A descriptive message explaining the error. Must not be null or empty.
     */
    public ResponseError(HttpStatus status, String message) {
        super(status, message);
    }

    public ResponseError(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
