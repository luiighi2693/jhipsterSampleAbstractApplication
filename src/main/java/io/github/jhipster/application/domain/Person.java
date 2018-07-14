package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Person.
 */
@Document(collection = "person")
public class Person  extends AttributeList implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String firstName = "FIRSTNAME";
    public static final String lastName = "LASTNAME";

    @Id
    private String id;

    @NotNull
    @Field("party_id")
    private String partyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartyId() {
        return partyId;
    }

    public Person partyId(String partyId) {
        this.partyId = partyId;
        return this;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public AttributeEntity getFirstName() {
        return getAttribute(firstName);
    }

    public AttributeEntity getLastName() {
        return getAttribute(lastName);
    }

    public String getFirstNameValue() {
        return getAttributeValue(firstName);
    }

    public void setFistNameValue(String value) {
        setAttributeValue(firstName, value);
    }

    public String getLastNameValue() {
        return getAttributeValue(lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        if (person.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", partyId='" + getPartyId() + "'" +
            ", attributes='" + getAttributes() + "'" +
            "}";
    }
}
