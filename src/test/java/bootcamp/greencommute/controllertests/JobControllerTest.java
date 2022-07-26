package bootcamp.greencommute.controllertests;

import bootcamp.greencommute.controller.JobController;
import bootcamp.greencommute.dao.JobRepository;
import bootcamp.greencommute.dto.JobDto;
import bootcamp.greencommute.entity.Job;
import bootcamp.greencommute.entity.JobCompany;
import bootcamp.greencommute.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import bootcamp.greencommute.service.MapperService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private MapperService mapperService;
    @MockBean
    private JobService jobService;
    @MockBean
    private JobController jobController;
    @MockBean
    private JobRepository jobRepository;

    private Job firstJob = new Job();
   private Job secondJob = new Job();
    UUID firstId=UUID.randomUUID();
    UUID secondId=UUID.randomUUID();


    private List<JobCompany> firstJobCompany;


    @BeforeEach
     void setup(){


        firstJob = firstJob.builder().
                id(firstId)
                .description("mock first description")
                .datePosted(LocalDateTime.now())
                .type("UX Designer")
                .build();
        secondJob = secondJob.builder().
                id(secondId)
                .description("mock second description")
                .datePosted(LocalDateTime.now())
                .type("UI Designer")
                .build();

    }

    @DisplayName("Test for getting all jobs")
    @Test
     void fetchAllJobs() throws Exception {
        List<Job> records = new ArrayList<>(Arrays.asList(firstJob, secondJob));

        Mockito.when(jobController.getJobs()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8035/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("mock first description")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("mock second description")));
    }

    @DisplayName("Test for getting job by id")
    @Test
     void fetchJobById() throws Exception {
        List<Job> records = new ArrayList<>(Arrays.asList(firstJob, secondJob));

        Mockito.when(jobController.getJobById(firstId)).thenReturn(firstJob);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8035/api/jobs/"+firstId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("mock first description")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("UX Designer")));
    }

    @DisplayName("Test for adding a job")
    @Test
     void returnAddedJob() throws Exception {
        UUID jobId = UUID.randomUUID();
        Job theJob = Job.builder().
                id(jobId)
                .description("mock description2")
                .datePosted(LocalDateTime.now())
                .type("UX Designer")
                .build();
        JobDto theJobDto = JobDto.builder().
                id(jobId.toString())
                .description("mock description2")
                .datePosted(LocalDateTime.now())
                .type("UI Designer")
                .build();

        Mockito.when(jobController.addJob(theJobDto)).thenReturn(theJob);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/jobs/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(theJobDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()));
    }
}
