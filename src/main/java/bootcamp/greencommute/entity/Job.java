package bootcamp.greencommute.entity;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job", schema="job")
public class Job implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private UUID id;

    @Column(name="description")
    @NotNull
    @Getter
    @Setter
    private String description;

    @Column(name="date_posted")
    @NotNull
    @Getter
    @Setter
    private LocalDateTime datePosted;

    @Column(name="type")
    @NotNull
    @Getter
    @Setter
    private String type;


}
