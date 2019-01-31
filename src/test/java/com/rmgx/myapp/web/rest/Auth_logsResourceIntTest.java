package com.rmgx.myapp.web.rest;

import com.rmgx.myapp.MyApp;

import com.rmgx.myapp.domain.Auth_logs;
import com.rmgx.myapp.repository.Auth_logsRepository;
import com.rmgx.myapp.service.Auth_logsService;
import com.rmgx.myapp.service.dto.Auth_logsDTO;
import com.rmgx.myapp.service.mapper.Auth_logsMapper;
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
 * Test class for the Auth_logsResource REST controller.
 *
 * @see Auth_logsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class Auth_logsResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_USER_AGENT = "AAAAAAAAAA";
    private static final String UPDATED_USER_AGENT = "BBBBBBBBBB";

    private static final String DEFAULT_AUTH_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_AUTH_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_STATUS = "BBBBBBBBBB";

    @Autowired
    private Auth_logsRepository auth_logsRepository;

    @Autowired
    private Auth_logsMapper auth_logsMapper;

    @Autowired
    private Auth_logsService auth_logsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAuth_logsMockMvc;

    private Auth_logs auth_logs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Auth_logsResource auth_logsResource = new Auth_logsResource(auth_logsService);
        this.restAuth_logsMockMvc = MockMvcBuilders.standaloneSetup(auth_logsResource)
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
    public static Auth_logs createEntity() {
        Auth_logs auth_logs = new Auth_logs()
            .user_id(DEFAULT_USER_ID)
            .ip_address(DEFAULT_IP_ADDRESS)
            .device(DEFAULT_DEVICE)
            .location(DEFAULT_LOCATION)
            .user_agent(DEFAULT_USER_AGENT)
            .auth_type(DEFAULT_AUTH_TYPE)
            .device_status(DEFAULT_DEVICE_STATUS);
        return auth_logs;
    }

    @Before
    public void initTest() {
        auth_logsRepository.deleteAll();
        auth_logs = createEntity();
    }

    @Test
    public void createAuth_logs() throws Exception {
        int databaseSizeBeforeCreate = auth_logsRepository.findAll().size();

        // Create the Auth_logs
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);
        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isCreated());

        // Validate the Auth_logs in the database
        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeCreate + 1);
        Auth_logs testAuth_logs = auth_logsList.get(auth_logsList.size() - 1);
        assertThat(testAuth_logs.getUser_id()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAuth_logs.getIp_address()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testAuth_logs.getDevice()).isEqualTo(DEFAULT_DEVICE);
        assertThat(testAuth_logs.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testAuth_logs.getUser_agent()).isEqualTo(DEFAULT_USER_AGENT);
        assertThat(testAuth_logs.getAuth_type()).isEqualTo(DEFAULT_AUTH_TYPE);
        assertThat(testAuth_logs.getDevice_status()).isEqualTo(DEFAULT_DEVICE_STATUS);
    }

    @Test
    public void createAuth_logsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auth_logsRepository.findAll().size();

        // Create the Auth_logs with an existing ID
        auth_logs.setId("existing_id");
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Auth_logs in the database
        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkUser_idIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setUser_id(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIp_addressIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setIp_address(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeviceIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setDevice(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setLocation(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUser_agentIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setUser_agent(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAuth_typeIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setAuth_type(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDevice_statusIsRequired() throws Exception {
        int databaseSizeBeforeTest = auth_logsRepository.findAll().size();
        // set the field null
        auth_logs.setDevice_status(null);

        // Create the Auth_logs, which fails.
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        restAuth_logsMockMvc.perform(post("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAuth_logs() throws Exception {
        // Initialize the database
        auth_logsRepository.save(auth_logs);

        // Get all the auth_logsList
        restAuth_logsMockMvc.perform(get("/api/auth-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auth_logs.getId())))
            .andExpect(jsonPath("$.[*].user_id").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].ip_address").value(hasItem(DEFAULT_IP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].device").value(hasItem(DEFAULT_DEVICE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].user_agent").value(hasItem(DEFAULT_USER_AGENT.toString())))
            .andExpect(jsonPath("$.[*].auth_type").value(hasItem(DEFAULT_AUTH_TYPE.toString())))
            .andExpect(jsonPath("$.[*].device_status").value(hasItem(DEFAULT_DEVICE_STATUS.toString())));
    }
    
    @Test
    public void getAuth_logs() throws Exception {
        // Initialize the database
        auth_logsRepository.save(auth_logs);

        // Get the auth_logs
        restAuth_logsMockMvc.perform(get("/api/auth-logs/{id}", auth_logs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(auth_logs.getId()))
            .andExpect(jsonPath("$.user_id").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.ip_address").value(DEFAULT_IP_ADDRESS.toString()))
            .andExpect(jsonPath("$.device").value(DEFAULT_DEVICE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.user_agent").value(DEFAULT_USER_AGENT.toString()))
            .andExpect(jsonPath("$.auth_type").value(DEFAULT_AUTH_TYPE.toString()))
            .andExpect(jsonPath("$.device_status").value(DEFAULT_DEVICE_STATUS.toString()));
    }

    @Test
    public void getNonExistingAuth_logs() throws Exception {
        // Get the auth_logs
        restAuth_logsMockMvc.perform(get("/api/auth-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAuth_logs() throws Exception {
        // Initialize the database
        auth_logsRepository.save(auth_logs);

        int databaseSizeBeforeUpdate = auth_logsRepository.findAll().size();

        // Update the auth_logs
        Auth_logs updatedAuth_logs = auth_logsRepository.findById(auth_logs.getId()).get();
        updatedAuth_logs
            .user_id(UPDATED_USER_ID)
            .ip_address(UPDATED_IP_ADDRESS)
            .device(UPDATED_DEVICE)
            .location(UPDATED_LOCATION)
            .user_agent(UPDATED_USER_AGENT)
            .auth_type(UPDATED_AUTH_TYPE)
            .device_status(UPDATED_DEVICE_STATUS);
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(updatedAuth_logs);

        restAuth_logsMockMvc.perform(put("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isOk());

        // Validate the Auth_logs in the database
        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeUpdate);
        Auth_logs testAuth_logs = auth_logsList.get(auth_logsList.size() - 1);
        assertThat(testAuth_logs.getUser_id()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAuth_logs.getIp_address()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testAuth_logs.getDevice()).isEqualTo(UPDATED_DEVICE);
        assertThat(testAuth_logs.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testAuth_logs.getUser_agent()).isEqualTo(UPDATED_USER_AGENT);
        assertThat(testAuth_logs.getAuth_type()).isEqualTo(UPDATED_AUTH_TYPE);
        assertThat(testAuth_logs.getDevice_status()).isEqualTo(UPDATED_DEVICE_STATUS);
    }

    @Test
    public void updateNonExistingAuth_logs() throws Exception {
        int databaseSizeBeforeUpdate = auth_logsRepository.findAll().size();

        // Create the Auth_logs
        Auth_logsDTO auth_logsDTO = auth_logsMapper.toDto(auth_logs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuth_logsMockMvc.perform(put("/api/auth-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auth_logsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Auth_logs in the database
        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAuth_logs() throws Exception {
        // Initialize the database
        auth_logsRepository.save(auth_logs);

        int databaseSizeBeforeDelete = auth_logsRepository.findAll().size();

        // Get the auth_logs
        restAuth_logsMockMvc.perform(delete("/api/auth-logs/{id}", auth_logs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Auth_logs> auth_logsList = auth_logsRepository.findAll();
        assertThat(auth_logsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Auth_logs.class);
        Auth_logs auth_logs1 = new Auth_logs();
        auth_logs1.setId("id1");
        Auth_logs auth_logs2 = new Auth_logs();
        auth_logs2.setId(auth_logs1.getId());
        assertThat(auth_logs1).isEqualTo(auth_logs2);
        auth_logs2.setId("id2");
        assertThat(auth_logs1).isNotEqualTo(auth_logs2);
        auth_logs1.setId(null);
        assertThat(auth_logs1).isNotEqualTo(auth_logs2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Auth_logsDTO.class);
        Auth_logsDTO auth_logsDTO1 = new Auth_logsDTO();
        auth_logsDTO1.setId("id1");
        Auth_logsDTO auth_logsDTO2 = new Auth_logsDTO();
        assertThat(auth_logsDTO1).isNotEqualTo(auth_logsDTO2);
        auth_logsDTO2.setId(auth_logsDTO1.getId());
        assertThat(auth_logsDTO1).isEqualTo(auth_logsDTO2);
        auth_logsDTO2.setId("id2");
        assertThat(auth_logsDTO1).isNotEqualTo(auth_logsDTO2);
        auth_logsDTO1.setId(null);
        assertThat(auth_logsDTO1).isNotEqualTo(auth_logsDTO2);
    }
}
