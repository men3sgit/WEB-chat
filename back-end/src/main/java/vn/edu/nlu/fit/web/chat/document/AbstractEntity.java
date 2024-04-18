package vn.edu.nlu.fit.web.chat.document;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import vn.edu.nlu.fit.web.chat.enums.EntityState;

import java.util.Date;

@Getter
@Setter
@Entity
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private EntityState entityState = EntityState.ACTIVE;



}
