package bootcamp.greencommute.controller;

import bootcamp.greencommute.dto.JobDto;
import bootcamp.greencommute.service.JobService;
import bootcamp.greencommute.service.MapperService;
import bootcamp.greencommute.entity.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private MapperService mapperService;

    @Autowired
    public JobController(JobService theJobService){
        jobService= theJobService;
    }

    @GetMapping("/jobs")
    public List<Job> getJobs(){
        log.info("<< inside GET JOBS controller >>");
        return jobService.getJobs();
    }

    @GetMapping("/jobtypes")
    public List<Job> getJobTypes(){
        log.info("<< inside GET JOBS controller >>");
        return jobService.getJobs();
    }

    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable("id") UUID id){
        log.info("<< inside GET JOB BY ID controller >>");
        log.info("<< ID: "+id+" >>");
        return jobService.fetchJobById(id);
    }

    @PostMapping("/jobs/")
    public Job addJob(@RequestBody JobDto theJobDto){
        log.info("<< inside ADD JOB controller >>");
        Job theJob = mapperService.convertToEntity(theJobDto);
        return jobService.addJob(theJob);
    }
}
