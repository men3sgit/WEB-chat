package com.nlu.fit.web.chat.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private Long id;
    private String username;
    private String fullName;
    private Status status;

}