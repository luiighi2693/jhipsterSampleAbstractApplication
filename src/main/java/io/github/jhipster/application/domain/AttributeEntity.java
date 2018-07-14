package io.github.jhipster.application.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Type;

/**
 * A AttributeEntity.
 */
public class AttributeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("code")
    private String code;

    @Field("value")
    private String value;

    @Field("type")
    private Type type;

    /**
     * EMBEDDED
     */
    @ApiModelProperty(value = "EMBEDDED")
    @Field("metadata")
    private Map<String, String> metadata;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public AttributeEntity code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public AttributeEntity value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public AttributeEntity type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public AttributeEntity metadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttributeEntity attributeEntity = (AttributeEntity) o;
        if (attributeEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attributeEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttributeEntity{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
