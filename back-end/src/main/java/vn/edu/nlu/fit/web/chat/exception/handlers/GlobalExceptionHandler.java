package vn.edu.nlu.fit.web.chat.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.nlu.fit.web.chat.exception.ApiError;
import vn.edu.nlu.fit.web.chat.exception.ApiRequestException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleApiRequestException(ApiRequestException e) {
        return buildApiError(e.getMessage());
    }

    private ApiError buildApiError(String message) {
        ApiError apiError = ApiError.builder()
                .message(message)
                .errors(Collections.emptyList())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return apiError;
    }
}
