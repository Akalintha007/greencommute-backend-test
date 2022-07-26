package bootcamp.greencommute.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job_company", schema="job")
public class JobCompany implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private UUID id;

    @Column(name="company_id")
    @NotNull
    @Getter
    @Setter
    private UUID companyId;

    @Column(name="job_id")
    @NotNull
    @Getter
    @Setter
    private UUID jobId;

}
