package io.office360.restapi.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"io.office360.restapi.persistence"})
@PropertySource({"classpath:persistence-${persistenceTarget:h2}.properties"})
@EnableJpaRepositories(basePackages = "io.office360.restapi.persistence.dao")
@EntityScan(basePackages = "io.office360.restapi.persistence.model")
public class Office360PersistenceJpaConfig {


    public Office360PersistenceJpaConfig() {
        super();
    }

    // beans

}
