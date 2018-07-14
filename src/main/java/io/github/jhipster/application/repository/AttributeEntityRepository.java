package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AttributeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AttributeEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeEntityRepository extends MongoRepository<AttributeEntity, String> {

}
