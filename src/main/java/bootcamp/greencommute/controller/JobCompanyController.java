package bootcamp.greencommute.controller;

import bootcamp.greencommute.dto.JobCompanyDto;
import bootcamp.greencommute.service.MapperService;
import bootcamp.greencommute.entity.JobCompany;
import bootcamp.greencommute.service.JobCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class JobCompanyController {

    @Autowired
    private JobCompanyService jobCompanyService;

    @Autowired
    private MapperService mapperService;

    @Autowired
    public JobCompanyController(JobCompanyService theJobCompanyService){
        jobCompanyService= theJobCompanyService;
    }

    @GetMapping("/jobcompanies")
    public List<JobCompany> getJobCompanies(){
        log.info("<< inside GET JOB COMPANIES controller >>");
        return jobCompanyService.getJobCompanies();
    }

    @GetMapping("/jobcompanies/{id}")
    public JobCompany getJobById(@PathVariable("id") UUID id){
        log.info("<< inside GET JOB COMPANIES BY ID controller >>");
        log.info("<< ID: "+id+" >>");
        return jobCompanyService.fetchJobCompanyById(id);
    }

    @PostMapping("/jobcompanies/")
    public JobCompany addJob(@RequestBody JobCompanyDto theJobCompanyDto){
        log.info("<< inside ADD JOB COMPANIES controller >>");
        JobCompany theJobCompany = mapperService.convertToJobCompanyEntity(theJobCompanyDto);
        return jobCompanyService.addJobCompany(theJobCompany);
    }
}
