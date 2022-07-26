package bootcamp.greencommute.service;


import bootcamp.greencommute.entity.JobCompany;

import java.util.List;
import java.util.UUID;

public interface JobCompanyService {
    public List<JobCompany> getJobCompanies();
    JobCompany fetchJobCompanyById(UUID id);
    JobCompany addJobCompany(JobCompany jobCompany);
}
