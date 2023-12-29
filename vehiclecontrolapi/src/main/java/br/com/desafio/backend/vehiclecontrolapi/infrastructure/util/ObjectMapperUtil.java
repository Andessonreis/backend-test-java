package br.com.desafio.backend.vehiclecontrolapi.infrastructure.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@Component
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;


    static {
        MODEL_MAPPER = new ModelMapper();
    }

    public <Input, Output> Output map(final Input object, final Class<Output> clazz){

        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);


        Output c =  MODEL_MAPPER.map(object, clazz);

        return c;

    }
    public <Source, Target> Target map(final Source s, Target t) {
    
        try {
        
            for (Field sourceField : s.getClass().getDeclaredFields()) {
                Field targetField = findFieldByName(t.getClass(), sourceField.getName());
                if (targetField == null) {
                    continue;
                }
    
                sourceField.setAccessible(true);
                targetField.setAccessible(true);
    
                if (isRecord(sourceField.getType())) {

                    Object sourceAggregateObject = sourceField.get(s);

                    Object targetAggregateObject = targetField.getType().getDeclaredConstructor().newInstance();
    
                    targetField.set(t, map(sourceAggregateObject, targetAggregateObject));
    
                } else {

                    Object value = sourceField.get(s);
                    targetField.set(t, value);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
        return t;
    }
    
    private Field findFieldByName(Class<?> clazz, String fieldName) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }
    

    private boolean isRecord(Class<?> clazz){

        return clazz.isRecord();

    }

    public <Input, Output> List<Output> mapAll(final Collection<Input> objectList, Class<Output> out){

        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return objectList.stream()
                .map(obj -> MODEL_MAPPER.map(obj, out))
                .toList();
    }



}