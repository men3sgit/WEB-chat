package vn.edu.nlu.fit.web.chat.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import vn.edu.nlu.fit.web.chat.enums.EntityState;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractDocument {

    @Id
    private String id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private EntityState entityState = EntityState.ACTIVE;


}
