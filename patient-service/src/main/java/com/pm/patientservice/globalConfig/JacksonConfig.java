package com.pm.patientservice.globalConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class JacksonConfig {

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
