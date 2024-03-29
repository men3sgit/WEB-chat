package vn.edu.nlu.fit.web.chat.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = buildErrorResponse(ex.getMessage());

        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::createErrorMap)
                .collect(Collectors.toList());
        errorResponse.put("errors", errors);

        return errorResponse;
    }


    private Map<String, Object> buildErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("status", HttpStatus.BAD_REQUEST);
        errorResponse.put("message", message);
        errorResponse.put("time", Instant.now());
        return errorResponse;
    }

    private Map<String, String> createErrorMap(FieldError fieldError) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("field", fieldError.getField());
        errorMap.put("message", fieldError.getDefaultMessage());
        return errorMap;
    }
}
