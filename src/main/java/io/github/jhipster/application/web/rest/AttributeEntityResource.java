package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.AttributeEntity;
import io.github.jhipster.application.repository.AttributeEntityRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AttributeEntity.
 */
@RestController
@RequestMapping("/api")
public class AttributeEntityResource {

    private final Logger log = LoggerFactory.getLogger(AttributeEntityResource.class);

    private static final String ENTITY_NAME = "attributeEntity";

    private final AttributeEntityRepository attributeEntityRepository;

    public AttributeEntityResource(AttributeEntityRepository attributeEntityRepository) {
        this.attributeEntityRepository = attributeEntityRepository;
    }

    /**
     * POST  /attribute-entities : Create a new attributeEntity.
     *
     * @param attributeEntity the attributeEntity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attributeEntity, or with status 400 (Bad Request) if the attributeEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attribute-entities")
    @Timed
    public ResponseEntity<AttributeEntity> createAttributeEntity(@RequestBody AttributeEntity attributeEntity) throws URISyntaxException {
        log.debug("REST request to save AttributeEntity : {}", attributeEntity);
        if (attributeEntity.getId() != null) {
            throw new BadRequestAlertException("A new attributeEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttributeEntity result = attributeEntityRepository.save(attributeEntity);
        return ResponseEntity.created(new URI("/api/attribute-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attribute-entities : Updates an existing attributeEntity.
     *
     * @param attributeEntity the attributeEntity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attributeEntity,
     * or with status 400 (Bad Request) if the attributeEntity is not valid,
     * or with status 500 (Internal Server Error) if the attributeEntity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attribute-entities")
    @Timed
    public ResponseEntity<AttributeEntity> updateAttributeEntity(@RequestBody AttributeEntity attributeEntity) throws URISyntaxException {
        log.debug("REST request to update AttributeEntity : {}", attributeEntity);
        if (attributeEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttributeEntity result = attributeEntityRepository.save(attributeEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attributeEntity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attribute-entities : get all the attributeEntities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of attributeEntities in body
     */
    @GetMapping("/attribute-entities")
    @Timed
    public List<AttributeEntity> getAllAttributeEntities() {
        log.debug("REST request to get all AttributeEntities");
        return attributeEntityRepository.findAll();
    }

    /**
     * GET  /attribute-entities/:id : get the "id" attributeEntity.
     *
     * @param id the id of the attributeEntity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attributeEntity, or with status 404 (Not Found)
     */
    @GetMapping("/attribute-entities/{id}")
    @Timed
    public ResponseEntity<AttributeEntity> getAttributeEntity(@PathVariable String id) {
        log.debug("REST request to get AttributeEntity : {}", id);
        Optional<AttributeEntity> attributeEntity = attributeEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(attributeEntity);
    }

    /**
     * DELETE  /attribute-entities/:id : delete the "id" attributeEntity.
     *
     * @param id the id of the attributeEntity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attribute-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttributeEntity(@PathVariable String id) {
        log.debug("REST request to delete AttributeEntity : {}", id);

        attributeEntityRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
