package io.office360.restapi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Import({ // @formatter:off
        Office360ContextConfig.class,
        Office360PersistenceJpaConfig.class,
        Office360ServiceConfig.class,
        Office360WebConfig.class
}) // @formatter:on
public class Office360App extends SpringBootServletInitializer {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        final ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/api/*");

        final Map<String, String> params = new HashMap<String, String>();
        params.put("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        params.put("contextConfigLocation", "org.spring.sec2.spring");
        params.put("dispatchOptionsRequest", "true");
        registration.setInitParameters(params);

        registration.setLoadOnStartup(1);
        return registration;
    }

    //

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Office360App.class);
    }

    public static void main(final String... args) {
        SpringApplication.run(Office360App.class, args);
    }

}
