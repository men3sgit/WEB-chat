package vn.edu.nlu.fit.web.chat.dto;

import lombok.Builder;
import vn.edu.nlu.fit.web.chat.documents.Status;

@Builder
public record UserDto(
        Long id,
        String email,
        String username,
        String firstName,
        String lastName,
        String phoneNumber,
        Status status,
        String roles
) {
}