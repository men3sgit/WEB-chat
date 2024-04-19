package vn.edu.nlu.fit.web.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import vn.edu.nlu.fit.web.chat.enums.EntityStatus;

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

    private EntityStatus entityStatus = EntityStatus.ACTIVE;



}
