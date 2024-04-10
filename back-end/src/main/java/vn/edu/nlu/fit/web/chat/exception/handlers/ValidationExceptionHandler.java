package vn.edu.nlu.fit.web.chat.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.nlu.fit.web.chat.exception.ApiError;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        // Extracting field errors into a list of error maps
        List<Map<String, String>> errors = fieldErrors.stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("field", fieldError.getField());
                    errorMap.put("message", fieldError.getDefaultMessage());
                    return errorMap;
                })
                .collect(Collectors.toList());

        // Constructing error response using ApiError builder
        return ApiError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation failed for request.")
                .time(Instant.now())
                .errors(errors)
                .errorCode(ApiError.ErrorCode.VALIDATION_ERROR)
                .build();
    }
}