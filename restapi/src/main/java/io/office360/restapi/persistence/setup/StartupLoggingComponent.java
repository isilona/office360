package io.office360.restapi.persistence.setup;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Component
public class StartupLoggingComponent implements InitializingBean {
    private static final String PERSISTENCE_TARGET_KEY = "persistenceTarget";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Autowired
    private Environment env;

    public StartupLoggingComponent() {
        super();
    }

    //

    @Override
    public void afterPropertiesSet() {
        logger.info("============================================================================");
        try {
            logPersistenceTarget(env);
        } catch (final Exception ex) {
            logger.warn("There was a problem logging data on startup", ex);
        }

        logger.info("============================================================================");
    }

    // UTIL

    private void logPersistenceTarget(final Environment environment) throws SQLException {
        final String envTarget = getValueOfProperty(environment, PERSISTENCE_TARGET_KEY, "h2", Lists.newArrayList("h2", "pg"));
        logger.info("{} = {}", PERSISTENCE_TARGET_KEY, envTarget);
        logger.info("DATASOURCE = {}", dataSource.getConnection());
    }

    private final String getValueOfProperty(final Environment environment, final String propertyKey, final String propertyDefaultValue, final List<String> acceptablePropertyValues) {
        String propValue = environment.getProperty(propertyKey, propertyDefaultValue);

        if (acceptablePropertyValues != null && !acceptablePropertyValues.contains(propValue)) {
            logger.warn("The property = {} has an invalid value = {}", propertyKey, propValue);
        }

        if (propValue == null) {
            logger.warn("The property = {} is null", propertyKey);
        }

        return propValue;
    }

}
