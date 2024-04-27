package vn.edu.nlu.fit.web.chat.dto.request;

import lombok.Data;
import vn.edu.nlu.fit.web.chat.enums.Gender;

import java.util.Date;

@Data
public class UpdateUserRequest {

    private String username;
    private Gender gender;
    private Date dateOfBirth;
    private String firstName;
    private String lastName;


}
