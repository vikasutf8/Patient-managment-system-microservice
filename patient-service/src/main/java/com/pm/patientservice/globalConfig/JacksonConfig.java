package com.pm.patientservice.globalConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class JacksonConfig {
/*
IMPROTANT
But for update, blindly mapping is dangerous.
If you do:
modelMapper.map(requestDto, patient);
⚠ Problems:
It may overwrite fields with null
It may overwrite id
It may overwrite createdDate
It may overwrite relationships
It may overwrite audit fields

modelMapper.getConfiguration()
        .setSkipNullEnabled(true);
 */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


//    @Bean
//    public ObjectMapper objectMapper() {
//        return JsonMapper.builder()
//                .findAndAddModules()
//                .serializationInclusion(JsonInclude.Include.NON_NULL)
//                .build();
//    }
//  NOTE: ADVANCE
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
//        return builder -> {
//            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//            builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        };
//    }
}
