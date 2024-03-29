package vn.edu.nlu.fit.web.chat.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.edu.nlu.fit.web.chat.exceptions.ApiError;
import vn.edu.nlu.fit.web.chat.exceptions.ApiRequestException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleApiRequestException(ApiRequestException e) {
        logger.warn("API request exception:", e);
        return buildApiError(e.getMessage());
    }

    @ExceptionHandler(Exception.class) // Catch-all for unhandled exceptions
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleOtherExceptions(Exception ex) {
        logger.error("Internal server error:", ex);
        return buildApiError("An unexpected error occurred. Please try again later.");
    }

    private ApiError buildApiError(String message) {
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.BAD_REQUEST.value()); // Consider using a custom error code scheme
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(message);
        apiError.setTime(Instant.now());
        apiError.setErrors(null);
        return apiError;
    }
}
