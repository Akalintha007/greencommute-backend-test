package bootcamp.greencommute.dao;

import bootcamp.greencommute.entity.JobCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobCompanyRepository extends JpaRepository<JobCompany, UUID> {



}
