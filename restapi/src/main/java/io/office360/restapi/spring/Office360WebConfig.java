package io.office360.restapi.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan({"io.office360.restapi.web", "io.office360.common.web"})
@EnableWebMvc
public class Office360WebConfig extends WebMvcConfigurerAdapter {

    public Office360WebConfig() {
        super();
    }

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {

        //Look for the converter responsible for Un/Marshaling JSON
        final Optional<HttpMessageConverter<?>> jackson2HttpMessageConverterFound = converters.stream().filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();
        if (jackson2HttpMessageConverterFound.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) jackson2HttpMessageConverterFound.get();

            //Enable pretty print output
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            // deserialization will fail whenever we are passing property that does not map to the DTO
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
