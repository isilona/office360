package io.office360.auth;

import io.office360.auth.spring.Office360ContextConfig;
import io.office360.auth.spring.Office360PersistenceJpaConfig;
import io.office360.auth.spring.Office360ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        Office360ContextConfig.class,
        Office360PersistenceJpaConfig.class,
        Office360ServiceConfig.class
})
public class Office360AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Office360AuthServerApplication.class, args);
    }
}
