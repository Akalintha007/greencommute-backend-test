package bootcamp.greencommute.dto;

import bootcamp.greencommute.entity.JobCompany;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class JobDto {

    private String id;
    private String description;
    private LocalDateTime datePosted;
    private String type;
    private List<JobCompany> jobCompanies;

}
