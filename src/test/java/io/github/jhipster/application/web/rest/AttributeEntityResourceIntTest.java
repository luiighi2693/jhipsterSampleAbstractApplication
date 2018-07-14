package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.AttributeEntity;
import io.github.jhipster.application.repository.AttributeEntityRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Type;
/**
 * Test class for the AttributeEntityResource REST controller.
 *
 * @see AttributeEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AttributeEntityResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Type DEFAULT_TYPE = Type.STRING;
    private static final Type UPDATED_TYPE = Type.INTEGER;

    private static final Map<String, String> DEFAULT_METADATA = new LinkedHashMap<String, String>();
    private static final Map<String, String> UPDATED_METADATA = new LinkedHashMap<String, String>();

    @Autowired
    private AttributeEntityRepository attributeEntityRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAttributeEntityMockMvc;

    private AttributeEntity attributeEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttributeEntityResource attributeEntityResource = new AttributeEntityResource(attributeEntityRepository);
        this.restAttributeEntityMockMvc = MockMvcBuilders.standaloneSetup(attributeEntityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeEntity createEntity() {
        AttributeEntity attributeEntity = new AttributeEntity()
            .code(DEFAULT_CODE)
            .value(DEFAULT_VALUE)
            .type(DEFAULT_TYPE)
            .metadata(DEFAULT_METADATA);
        return attributeEntity;
    }

    @Before
    public void initTest() {
        attributeEntityRepository.deleteAll();
        attributeEntity = createEntity();
    }

    @Test
    public void createAttributeEntity() throws Exception {
        int databaseSizeBeforeCreate = attributeEntityRepository.findAll().size();

        // Create the AttributeEntity
        restAttributeEntityMockMvc.perform(post("/api/attribute-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attributeEntity)))
            .andExpect(status().isCreated());

        // Validate the AttributeEntity in the database
        List<AttributeEntity> attributeEntityList = attributeEntityRepository.findAll();
        assertThat(attributeEntityList).hasSize(databaseSizeBeforeCreate + 1);
        AttributeEntity testAttributeEntity = attributeEntityList.get(attributeEntityList.size() - 1);
        assertThat(testAttributeEntity.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAttributeEntity.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testAttributeEntity.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAttributeEntity.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    public void createAttributeEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attributeEntityRepository.findAll().size();

        // Create the AttributeEntity with an existing ID
        attributeEntity.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttributeEntityMockMvc.perform(post("/api/attribute-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attributeEntity)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeEntity in the database
        List<AttributeEntity> attributeEntityList = attributeEntityRepository.findAll();
        assertThat(attributeEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAttributeEntities() throws Exception {
        // Initialize the database
        attributeEntityRepository.save(attributeEntity);

        // Get all the attributeEntityList
        restAttributeEntityMockMvc.perform(get("/api/attribute-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attributeEntity.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA.toString())));
    }


    @Test
    public void getAttributeEntity() throws Exception {
        // Initialize the database
        attributeEntityRepository.save(attributeEntity);

        // Get the attributeEntity
        restAttributeEntityMockMvc.perform(get("/api/attribute-entities/{id}", attributeEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attributeEntity.getId()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA.toString()));
    }
    @Test
    public void getNonExistingAttributeEntity() throws Exception {
        // Get the attributeEntity
        restAttributeEntityMockMvc.perform(get("/api/attribute-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAttributeEntity() throws Exception {
        // Initialize the database
        attributeEntityRepository.save(attributeEntity);

        int databaseSizeBeforeUpdate = attributeEntityRepository.findAll().size();

        // Update the attributeEntity
        AttributeEntity updatedAttributeEntity = attributeEntityRepository.findById(attributeEntity.getId()).get();
        updatedAttributeEntity
            .code(UPDATED_CODE)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .metadata(UPDATED_METADATA);

        restAttributeEntityMockMvc.perform(put("/api/attribute-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttributeEntity)))
            .andExpect(status().isOk());

        // Validate the AttributeEntity in the database
        List<AttributeEntity> attributeEntityList = attributeEntityRepository.findAll();
        assertThat(attributeEntityList).hasSize(databaseSizeBeforeUpdate);
        AttributeEntity testAttributeEntity = attributeEntityList.get(attributeEntityList.size() - 1);
        assertThat(testAttributeEntity.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAttributeEntity.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testAttributeEntity.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAttributeEntity.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    public void updateNonExistingAttributeEntity() throws Exception {
        int databaseSizeBeforeUpdate = attributeEntityRepository.findAll().size();

        // Create the AttributeEntity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttributeEntityMockMvc.perform(put("/api/attribute-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attributeEntity)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeEntity in the database
        List<AttributeEntity> attributeEntityList = attributeEntityRepository.findAll();
        assertThat(attributeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAttributeEntity() throws Exception {
        // Initialize the database
        attributeEntityRepository.save(attributeEntity);

        int databaseSizeBeforeDelete = attributeEntityRepository.findAll().size();

        // Get the attributeEntity
        restAttributeEntityMockMvc.perform(delete("/api/attribute-entities/{id}", attributeEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttributeEntity> attributeEntityList = attributeEntityRepository.findAll();
        assertThat(attributeEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttributeEntity.class);
        AttributeEntity attributeEntity1 = new AttributeEntity();
        attributeEntity1.setId("id1");
        AttributeEntity attributeEntity2 = new AttributeEntity();
        attributeEntity2.setId(attributeEntity1.getId());
        assertThat(attributeEntity1).isEqualTo(attributeEntity2);
        attributeEntity2.setId("id2");
        assertThat(attributeEntity1).isNotEqualTo(attributeEntity2);
        attributeEntity1.setId(null);
        assertThat(attributeEntity1).isNotEqualTo(attributeEntity2);
    }
}
