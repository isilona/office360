package io.office360.restapi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ // @formatter:off
        Office360ContextConfig.class,
        Office360PersistenceJpaConfig.class,
        Office360ServiceConfig.class,
        Office360WebConfig.class,
        Office360AuthorizationConfig.class,
        Office360ResourceConfig.class
}) // @formatter:on
public class Office360App extends SpringBootServletInitializer {

    public static void main(final String... args) {
        SpringApplication.run(Office360App.class, args);
    }

}
