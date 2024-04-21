package vn.edu.nlu.fit.web.chat.dto;

import lombok.Builder;
import vn.edu.nlu.fit.web.chat.enums.UserStatus;

@Builder
public record UserDto(
        Long id,
        String email,
        String username,
        String firstName,
        String lastName,
        String phoneNumber,
        UserStatus userStatus,
        String roles
) {
}