package bootcamp.greencommute.service;

import bootcamp.greencommute.dao.JobCompanyRepository;
import bootcamp.greencommute.entity.JobCompany;
import bootcamp.greencommute.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class JobCompanyServiceImpl implements JobCompanyService{

    private JobCompanyRepository jobCompanyRepository;

    @Autowired
    public JobCompanyServiceImpl(JobCompanyRepository theJobCompanyRepository){
        jobCompanyRepository = theJobCompanyRepository;
    }


    @Override
    public List<JobCompany> getJobCompanies() {
        log.info("<< INSIDE GET JOB COMPANY METHOD >>");
        return jobCompanyRepository.findAll();
    }

    @Override
    public JobCompany fetchJobCompanyById(UUID id) {
        log.info("<< INSIDE FETCH JOB COMPANY BY ID METHOD >>");
        return jobCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No job found "));

    }

    @Override
    public JobCompany addJobCompany(JobCompany jobCompany) {
        try{
            return jobCompanyRepository.save(jobCompany);
        }
        catch(Exception e){
            log.info("in catch block");
            throw new NotFoundException("Unable to add job");
        }

    }

}
