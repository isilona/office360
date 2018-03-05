package io.office360.restapi.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.office360.restapi.service"})
public class Office360ServiceConfig {

    public Office360ServiceConfig() {
        super();
    }

    // beans

}
