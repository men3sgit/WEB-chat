package vn.edu.nlu.fit.web.chat.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseData<T> {
    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * Constructs a response without data.
     *
     * @param status  The HTTP status code.
     * @param message A descriptive message about the response.
     */
    public ResponseData(int status, String message) {
        this(status, message, null);
    }

    /**
     * Constructs a response with data.
     *
     * @param status  The HTTP status code.
     * @param message A descriptive message about the response.
     * @param data    The data to be included in the response.
     */
    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Constructs a response without data.
     *
     * @param httpStatus The HTTP status.
     * @param message    A descriptive message about the response.
     */
    public ResponseData(HttpStatus httpStatus, String message) {
        this(httpStatus.value(), message);
    }

    /**
     * Constructs a response with data.
     *
     * @param httpStatus The HTTP status.
     * @param message    A descriptive message about the response.
     * @param data       The data to be included in the response.
     */
    public ResponseData(HttpStatus httpStatus, String message, T data) {
        this(httpStatus.value(), message, data);
    }

}
