package vn.edu.nlu.fit.web.chat.controllers;

import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MessageMapping("/user.connect")
    @SendTo("/user/topic")
    public User connect(@Payload User user){
        userService.connect(user);
        return user;
    }

    @MessageMapping("/user.disconnect")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.disconnect(user);
        return user;
    }


    @GetMapping("/connected-users")
    public ResponseEntity<List<User>> getConnectedUsers(){
        return ResponseEntity.ok(
                userService.getConnectedUsers()
        );
    }



}