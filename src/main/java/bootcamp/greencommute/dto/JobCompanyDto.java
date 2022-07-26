package bootcamp.greencommute.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class JobCompanyDto {

    private UUID id;
    private UUID companyId;
    private UUID jobId;
}
