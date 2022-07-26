package bootcamp.greencommute.service;

import bootcamp.greencommute.entity.Job;

import java.util.List;
import java.util.UUID;

public interface JobService {
    public List<Job> getJobs();
    Job fetchJobById(UUID id);
    Job addJob(Job job);
}
