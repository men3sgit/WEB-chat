package vn.edu.nlu.fit.web.chat.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ApiError {

    public enum ErrorCode {
        VALIDATION_ERROR,
        API_REQUEST_ERROR,
        INTERNAL_SERVER_ERROR,
    }

    private int code;
    private HttpStatus status;
    private String message;
    private Instant time;
    private List<Map<String, String>> errors; //
    private ErrorCode errorCode;

}
