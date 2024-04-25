package vn.edu.nlu.fit.web.chat.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ApiError {
    public enum ErrorCode {
        VALIDATION_ERROR,
        API_REQUEST_ERROR,
        INTERNAL_SERVER_ERROR,
        UNAUTHORIZED_ERROR
    }

    private int code;
    private HttpStatus status;
    private String message;
    private Instant time;
    private List<Map<String, String>> errors; //

}
