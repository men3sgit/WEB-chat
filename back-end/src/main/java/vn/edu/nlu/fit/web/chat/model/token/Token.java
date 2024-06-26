package vn.edu.nlu.fit.web.chat.model.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TimeZoneColumn;
import vn.edu.nlu.fit.web.chat.model.AbstractEntity;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends AbstractEntity {
    @Column(name = "expired_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant expiredAt;

    @Column(name = "value")
    private String value;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @Column(name = "owner")
    private Long owner;

}


