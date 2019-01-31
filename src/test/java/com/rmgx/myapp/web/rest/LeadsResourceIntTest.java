package com.rmgx.myapp.web.rest;

import com.rmgx.myapp.MyApp;

import com.rmgx.myapp.domain.Leads;
import com.rmgx.myapp.repository.LeadsRepository;
import com.rmgx.myapp.service.LeadsService;
import com.rmgx.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;


import static com.rmgx.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LeadsResource REST controller.
 *
 * @see LeadsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class LeadsResourceIntTest {

    private static final String DEFAULT_SALUTATION = "AAAAAAAAAA";
    private static final String UPDATED_SALUTATION = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_HOME_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CELL_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CELL_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PREFERRED_COMMS_MODE = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_COMMS_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_INTEREST = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_INTEREST = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_QUALITY_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_QUALITY_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_TO = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_TO = "BBBBBBBBBB";

    @Autowired
    private LeadsRepository leadsRepository;

    @Autowired
    private LeadsService leadsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restLeadsMockMvc;

    private Leads leads;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LeadsResource leadsResource = new LeadsResource(leadsService);
        this.restLeadsMockMvc = MockMvcBuilders.standaloneSetup(leadsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Leads createEntity() {
        Leads leads = new Leads()
            .salutation(DEFAULT_SALUTATION)
            .fullName(DEFAULT_FULL_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .homePhone(DEFAULT_HOME_PHONE)
            .workPhone(DEFAULT_WORK_PHONE)
            .cellPhone(DEFAULT_CELL_PHONE)
            .gender(DEFAULT_GENDER)
            .type(DEFAULT_TYPE)
            .preferredCommsMode(DEFAULT_PREFERRED_COMMS_MODE)
            .stage(DEFAULT_STAGE)
            .status(DEFAULT_STATUS)
            .leadInterest(DEFAULT_LEAD_INTEREST)
            .leadQualityScore(DEFAULT_LEAD_QUALITY_SCORE)
            .assignedTo(DEFAULT_ASSIGNED_TO);
        return leads;
    }

    @Before
    public void initTest() {
        leadsRepository.deleteAll();
        leads = createEntity();
    }

    @Test
    public void createLeads() throws Exception {
        int databaseSizeBeforeCreate = leadsRepository.findAll().size();

        // Create the Leads
        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isCreated());

        // Validate the Leads in the database
        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeCreate + 1);
        Leads testLeads = leadsList.get(leadsList.size() - 1);
        assertThat(testLeads.getSalutation()).isEqualTo(DEFAULT_SALUTATION);
        assertThat(testLeads.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testLeads.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testLeads.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testLeads.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testLeads.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testLeads.getHomePhone()).isEqualTo(DEFAULT_HOME_PHONE);
        assertThat(testLeads.getWorkPhone()).isEqualTo(DEFAULT_WORK_PHONE);
        assertThat(testLeads.getCellPhone()).isEqualTo(DEFAULT_CELL_PHONE);
        assertThat(testLeads.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testLeads.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testLeads.getPreferredCommsMode()).isEqualTo(DEFAULT_PREFERRED_COMMS_MODE);
        assertThat(testLeads.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testLeads.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLeads.getLeadInterest()).isEqualTo(DEFAULT_LEAD_INTEREST);
        assertThat(testLeads.getLeadQualityScore()).isEqualTo(DEFAULT_LEAD_QUALITY_SCORE);
        assertThat(testLeads.getAssignedTo()).isEqualTo(DEFAULT_ASSIGNED_TO);
    }

    @Test
    public void createLeadsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leadsRepository.findAll().size();

        // Create the Leads with an existing ID
        leads.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        // Validate the Leads in the database
        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkSalutationIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setSalutation(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setFullName(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setFirstName(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setLastName(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateOfBirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setDateOfBirth(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setEmailAddress(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkHomePhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setHomePhone(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWorkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setWorkPhone(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCellPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setCellPhone(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setGender(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setType(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPreferredCommsModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setPreferredCommsMode(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStageIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setStage(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setStatus(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLeadInterestIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setLeadInterest(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLeadQualityScoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setLeadQualityScore(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAssignedToIsRequired() throws Exception {
        int databaseSizeBeforeTest = leadsRepository.findAll().size();
        // set the field null
        leads.setAssignedTo(null);

        // Create the Leads, which fails.

        restLeadsMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllLeads() throws Exception {
        // Initialize the database
        leadsRepository.save(leads);

        // Get all the leadsList
        restLeadsMockMvc.perform(get("/api/leads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leads.getId())))
            .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].homePhone").value(hasItem(DEFAULT_HOME_PHONE.toString())))
            .andExpect(jsonPath("$.[*].workPhone").value(hasItem(DEFAULT_WORK_PHONE.toString())))
            .andExpect(jsonPath("$.[*].cellPhone").value(hasItem(DEFAULT_CELL_PHONE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].preferredCommsMode").value(hasItem(DEFAULT_PREFERRED_COMMS_MODE.toString())))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].leadInterest").value(hasItem(DEFAULT_LEAD_INTEREST.toString())))
            .andExpect(jsonPath("$.[*].leadQualityScore").value(hasItem(DEFAULT_LEAD_QUALITY_SCORE.toString())))
            .andExpect(jsonPath("$.[*].assignedTo").value(hasItem(DEFAULT_ASSIGNED_TO.toString())));
    }
    
    @Test
    public void getLeads() throws Exception {
        // Initialize the database
        leadsRepository.save(leads);

        // Get the leads
        restLeadsMockMvc.perform(get("/api/leads/{id}", leads.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(leads.getId()))
            .andExpect(jsonPath("$.salutation").value(DEFAULT_SALUTATION.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.homePhone").value(DEFAULT_HOME_PHONE.toString()))
            .andExpect(jsonPath("$.workPhone").value(DEFAULT_WORK_PHONE.toString()))
            .andExpect(jsonPath("$.cellPhone").value(DEFAULT_CELL_PHONE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.preferredCommsMode").value(DEFAULT_PREFERRED_COMMS_MODE.toString()))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.leadInterest").value(DEFAULT_LEAD_INTEREST.toString()))
            .andExpect(jsonPath("$.leadQualityScore").value(DEFAULT_LEAD_QUALITY_SCORE.toString()))
            .andExpect(jsonPath("$.assignedTo").value(DEFAULT_ASSIGNED_TO.toString()));
    }

    @Test
    public void getNonExistingLeads() throws Exception {
        // Get the leads
        restLeadsMockMvc.perform(get("/api/leads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLeads() throws Exception {
        // Initialize the database
        leadsService.save(leads);

        int databaseSizeBeforeUpdate = leadsRepository.findAll().size();

        // Update the leads
        Leads updatedLeads = leadsRepository.findById(leads.getId()).get();
        updatedLeads
            .salutation(UPDATED_SALUTATION)
            .fullName(UPDATED_FULL_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .homePhone(UPDATED_HOME_PHONE)
            .workPhone(UPDATED_WORK_PHONE)
            .cellPhone(UPDATED_CELL_PHONE)
            .gender(UPDATED_GENDER)
            .type(UPDATED_TYPE)
            .preferredCommsMode(UPDATED_PREFERRED_COMMS_MODE)
            .stage(UPDATED_STAGE)
            .status(UPDATED_STATUS)
            .leadInterest(UPDATED_LEAD_INTEREST)
            .leadQualityScore(UPDATED_LEAD_QUALITY_SCORE)
            .assignedTo(UPDATED_ASSIGNED_TO);

        restLeadsMockMvc.perform(put("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLeads)))
            .andExpect(status().isOk());

        // Validate the Leads in the database
        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeUpdate);
        Leads testLeads = leadsList.get(leadsList.size() - 1);
        assertThat(testLeads.getSalutation()).isEqualTo(UPDATED_SALUTATION);
        assertThat(testLeads.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testLeads.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testLeads.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testLeads.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testLeads.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testLeads.getHomePhone()).isEqualTo(UPDATED_HOME_PHONE);
        assertThat(testLeads.getWorkPhone()).isEqualTo(UPDATED_WORK_PHONE);
        assertThat(testLeads.getCellPhone()).isEqualTo(UPDATED_CELL_PHONE);
        assertThat(testLeads.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testLeads.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLeads.getPreferredCommsMode()).isEqualTo(UPDATED_PREFERRED_COMMS_MODE);
        assertThat(testLeads.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testLeads.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLeads.getLeadInterest()).isEqualTo(UPDATED_LEAD_INTEREST);
        assertThat(testLeads.getLeadQualityScore()).isEqualTo(UPDATED_LEAD_QUALITY_SCORE);
        assertThat(testLeads.getAssignedTo()).isEqualTo(UPDATED_ASSIGNED_TO);
    }

    @Test
    public void updateNonExistingLeads() throws Exception {
        int databaseSizeBeforeUpdate = leadsRepository.findAll().size();

        // Create the Leads

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeadsMockMvc.perform(put("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leads)))
            .andExpect(status().isBadRequest());

        // Validate the Leads in the database
        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLeads() throws Exception {
        // Initialize the database
        leadsService.save(leads);

        int databaseSizeBeforeDelete = leadsRepository.findAll().size();

        // Get the leads
        restLeadsMockMvc.perform(delete("/api/leads/{id}", leads.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Leads> leadsList = leadsRepository.findAll();
        assertThat(leadsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Leads.class);
        Leads leads1 = new Leads();
        leads1.setId("id1");
        Leads leads2 = new Leads();
        leads2.setId(leads1.getId());
        assertThat(leads1).isEqualTo(leads2);
        leads2.setId("id2");
        assertThat(leads1).isNotEqualTo(leads2);
        leads1.setId(null);
        assertThat(leads1).isNotEqualTo(leads2);
    }
}
