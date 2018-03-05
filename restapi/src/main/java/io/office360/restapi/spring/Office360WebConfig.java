package io.office360.restapi.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({ "io.office360.restapi.web" })
@EnableWebMvc
public class Office360WebConfig {

    public Office360WebConfig() {
        super();
    }
}
