package io.office360.restapi.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan({"io.office360.restapi.web", "io.office360.common.web"})
@EnableWebMvc
public class Office360WebConfig implements WebMvcConfigurer {

    public Office360WebConfig() {
        super();
    }

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {

        //Look for the converter responsible for Un/Marshaling JSON
        converters
                .stream()
                .filter(c -> c instanceof AbstractJackson2HttpMessageConverter)
                .findFirst()
                .ifPresent(httpMessageConverter -> {
                    final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) httpMessageConverter;

                    //Enable pretty print output
                    converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

                    // deserialization will fail whenever we are passing property that does not map to the DTO
                    converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                });

        //Look for the converter responsible for Un/Marshaling XML
        converters
                .stream()
                .filter(c -> c instanceof MappingJackson2XmlHttpMessageConverter)
                .findFirst()
                .ifPresent(httpMessageConverter -> {
                    final MappingJackson2XmlHttpMessageConverter converter = (MappingJackson2XmlHttpMessageConverter) httpMessageConverter;

                    //Enable pretty print output
                    converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

                    // deserialization will fail whenever we are passing property that does not map to the DTO
                    converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                });
    }
}
