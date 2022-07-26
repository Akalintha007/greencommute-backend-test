package bootcamp.greencommute.service;

import bootcamp.greencommute.dao.JobRepository;
import bootcamp.greencommute.exception.NotFoundException;
import bootcamp.greencommute.entity.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class JobServiceImpl implements JobService{

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository theJobRepository){
        jobRepository = theJobRepository;
    }


    @Override
    public List<Job> getJobs() {
        log.info("<< INSIDE GET JOBS METHOD >>");
        return jobRepository.findAll();
    }

    @Override
    public Job fetchJobById(UUID id) {
        log.info("<< INSIDE FETCH JOB BY ID METHOD >>");
        return jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No job found "));

    }

    @Override
    public Job addJob(Job job) {
        try{
            return jobRepository.save(job);
        }
        catch(Exception e){
            log.info("in catch block");
            throw new NotFoundException("Unable to add job");
        }
    }

}
