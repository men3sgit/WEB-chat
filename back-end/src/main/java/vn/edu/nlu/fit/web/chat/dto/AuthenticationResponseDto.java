package vn.edu.nlu.fit.web.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vn.edu.nlu.fit.web.chat.documents.UserDto;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDto {

    private final boolean success;
    private final String message; // Optional error message
    private final UserDto user; // Optional user object
}