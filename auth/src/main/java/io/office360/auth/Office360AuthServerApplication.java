package io.office360.auth;

import io.office360.auth.spring.Office360AuthContextConfig;
import io.office360.auth.spring.Office360AuthPersistenceJpaConfig;
import io.office360.auth.spring.Office360AuthServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        Office360AuthContextConfig.class,
        Office360AuthPersistenceJpaConfig.class,
        Office360AuthServiceConfig.class
})
public class Office360AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Office360AuthServerApplication.class, args);
    }
}
