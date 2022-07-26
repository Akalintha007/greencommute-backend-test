package bootcamp.greencommute.controllertests;

import bootcamp.greencommute.dao.JobCompanyRepository;
import bootcamp.greencommute.dto.JobCompanyDto;
import bootcamp.greencommute.service.MapperService;
import bootcamp.greencommute.controller.JobCompanyController;
import bootcamp.greencommute.entity.JobCompany;
import bootcamp.greencommute.service.JobCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobCompanyController.class)
public class JobCompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private MapperService mapperService;
    @MockBean
    private JobCompanyService jobCompanyService;
    @MockBean
    private JobCompanyController jobCompanyController;
    @MockBean
    private JobCompanyRepository jobCompanyRepository;

    private JobCompany firstJobCompany = new JobCompany();
   private JobCompany secondJobCompany = new JobCompany();
    UUID firstId=UUID.randomUUID();
    UUID secondId=UUID.randomUUID();

    UUID companyId=UUID.randomUUID();

    UUID jobId=UUID.randomUUID();

    @BeforeEach
     void setup(){


        firstJobCompany = firstJobCompany.builder().
                id(firstId)
                .companyId(companyId)
                .jobId(jobId)
                .build();
        secondJobCompany = secondJobCompany.builder().
                id(secondId)
                .companyId(companyId)
                .jobId(jobId)
                .build();

    }

    @DisplayName("Test for getting all jobs")
    @Test
     void fetchAllJobs() throws Exception {
        List<JobCompany> records = new ArrayList<>(Arrays.asList(firstJobCompany, secondJobCompany));

        Mockito.when(jobCompanyController.getJobCompanies()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8035/api/jobcompanies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].companyId", Matchers.is(companyId.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].companyId", Matchers.is(companyId.toString())));
    }

    @DisplayName("Test for getting job by id")
    @Test
     void fetchJobById() throws Exception {
        List<JobCompany> records = new ArrayList<>(Arrays.asList(firstJobCompany, secondJobCompany));

        Mockito.when(jobCompanyController.getJobById(firstId)).thenReturn(firstJobCompany);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8035/api/jobcompanies/"+firstId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId", Matchers.is(companyId.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobId", Matchers.is(jobId.toString())));
    }

    @DisplayName("Test for adding a job")
    @Test
     void returnAddedJob() throws Exception {
        UUID jobId = UUID.randomUUID();
        JobCompany theJobCompany = JobCompany.builder().
                id(firstId)
                .companyId(companyId)
                .jobId(jobId)
                .build();
        JobCompanyDto theJobCompanyDto = JobCompanyDto.builder().
                id(firstId)
                .companyId(companyId)
                .jobId(jobId)
                .build();

        Mockito.when(jobCompanyController.addJob(theJobCompanyDto)).thenReturn(theJobCompany);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/jobcompanies/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(theJobCompanyDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()));
    }
}
