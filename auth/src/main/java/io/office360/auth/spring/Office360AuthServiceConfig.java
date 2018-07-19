package io.office360.auth.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.office360.auth.service", "io.office360.auth.web.controller.data.mapping"})
// TODO : Consider moving mapping to persistence package
public class Office360AuthServiceConfig {

    public Office360AuthServiceConfig() {
        super();
    }

    // beans

}
