package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.cschillingtschuehly.gcwebx.modell.Member;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Service;

import static org.dozer.loader.api.TypeMappingOptions.mapNull;

@Service
public class MapperService {
    public ObjectMapper jacksonMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
    public DozerBeanMapper dozerMapper(){
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
    private BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Member.class, Member.class, mapNull(false));
            }
        };
    }

}
