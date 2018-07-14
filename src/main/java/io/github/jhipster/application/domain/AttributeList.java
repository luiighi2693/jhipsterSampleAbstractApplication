package io.github.jhipster.application.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.application.domain.util.AttributeUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A Person.
 */
public class AttributeList {

    /**
     * EMBEDDED
     */
    @ApiModelProperty(value = "EMBEDDED")
    @Field("attributes")
    private String attributes;

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getAttributeValue (String field){
       return AttributeUtil.getAttributeValue(field, attributes);
    }

    public void setAttributeValue (String field, String value){
        String attributes = AttributeUtil.setAttributeValue(field, value, this.attributes);
        if (!attributes.equals("")) this.attributes = attributes;
    }

    public AttributeEntity getAttribute (String field){
        return AttributeUtil.getAttributeEntity(field, attributes);
    }

    public void setAttribute (String field, AttributeEntity value){
        String attributes = AttributeUtil.setAttributeEntity(field, value, this.attributes);
        if (!attributes.equals("")) this.attributes = attributes;
    }
}
