package vn.edu.nlu.fit.web.chat.exceptions;


import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ApiRequestException extends RuntimeException {
    private int code;
    private Collection<Object> errorFields = new ArrayList<>();
    private String type;

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Collection<Object> errorFields) {
        super(message);
        this.errorFields = errorFields.stream().filter(Objects::nonNull).collect(Collectors.toList());

    }

    public ApiRequestException(String message, Throwable e) {
        super(message, e);
        this.errorFields = errorFields.stream().filter(Objects::nonNull).collect(Collectors.toList());

    }

    public ApiRequestException(String message, int code, Collection<Object> errorFields) {
        this(message, errorFields);
        this.code = code;

    }

}