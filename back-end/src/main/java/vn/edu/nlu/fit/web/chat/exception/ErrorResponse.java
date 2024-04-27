package vn.edu.nlu.fit.web.chat.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ErrorResponse {
    private int code;
    private HttpStatus status;
    private String message;
    private String path;
    private Instant timestamp;

}
