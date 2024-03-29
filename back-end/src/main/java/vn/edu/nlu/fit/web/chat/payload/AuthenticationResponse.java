package vn.edu.nlu.fit.web.chat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @param token
 */
@Getter
@AllArgsConstructor
public record AuthenticationResponse(String token) {

}