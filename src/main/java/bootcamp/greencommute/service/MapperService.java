package bootcamp.greencommute.service;

import bootcamp.greencommute.dto.JobCompanyDto;
import bootcamp.greencommute.dto.JobDto;
import bootcamp.greencommute.entity.Job;
import bootcamp.greencommute.entity.JobCompany;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MapperService {

    @Autowired
    private ModelMapper modelMapper;

    public Job convertToEntity(JobDto theJobDto){
        try{
            Job job = modelMapper.map(theJobDto, Job.class);
            return job;
        }
        catch (NullPointerException ne){
            throw new NullPointerException("null pointer in job convertToEntity");
        }
    }


    public JobCompany convertToJobCompanyEntity(JobCompanyDto theJobCompanyDto){
        try{
            JobCompany jobCompany = modelMapper.map(theJobCompanyDto, JobCompany.class);
            return jobCompany;
        }
        catch (NullPointerException ne){
            throw new NullPointerException("null pointer in jobCompany convertToEntity");
        }
    }
}
