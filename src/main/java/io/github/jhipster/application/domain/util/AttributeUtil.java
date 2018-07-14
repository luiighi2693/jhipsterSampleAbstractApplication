package io.github.jhipster.application.domain.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.application.domain.AttributeEntity;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class AttributeUtil {
    public static String getAttributeValue (String field, String attributes){
        try {
            Map<String, AttributeEntity> map =  new ObjectMapper().readValue(attributes, new TypeReference<Map<String, AttributeEntity>>(){});
            Optional<Map.Entry<String,AttributeEntity>> result = map
                .entrySet()
                .stream().parallel()
                .filter( a -> field.equals(a.getValue().getCode())).findFirst();
            if ( result.isPresent()) return result.get().getValue().getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String setAttributeValue(String field, String value, String attributes){
        try {
            Map<String, AttributeEntity> map =  new ObjectMapper().readValue(attributes, new TypeReference<Map<String, AttributeEntity>>(){});
            Optional<Map.Entry<String,AttributeEntity>> result = map
                .entrySet()
                .stream().parallel()
                .filter( a -> field.equals(a.getValue().getCode())).findFirst();
            if ( result.isPresent()){
                result.get().getValue().setValue(value);
                map.put(result.get().getKey(), result.get().getValue());
                return new ObjectMapper().writeValueAsString(map);
            }else{
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static AttributeEntity getAttributeEntity (String field, String attributes){
        try {
            Map<String, AttributeEntity> map =  new ObjectMapper().readValue(attributes, new TypeReference<Map<String, AttributeEntity>>(){});
            Optional<Map.Entry<String,AttributeEntity>> result = map
                .entrySet()
                .stream().parallel()
                .filter( a -> field.equals(a.getValue().getCode())).findFirst();
            if ( result.isPresent()) return result.get().getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String setAttributeEntity(String field, AttributeEntity attributeEntity, String attributes){
        try {
            Map<String, AttributeEntity> map =  new ObjectMapper().readValue(attributes, new TypeReference<Map<String, AttributeEntity>>(){});
            Optional<Map.Entry<String,AttributeEntity>> result = map
                .entrySet()
                .stream().parallel()
                .filter( a -> field.equals(a.getValue().getCode())).findFirst();
            if ( result.isPresent()){
                map.put(result.get().getKey(), attributeEntity);
                return new ObjectMapper().writeValueAsString(map);
            }else{
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
