package com.romantupikov.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

@PropertySource("classpath:config/persistence-psql.properties")
public class BasePersistenceConfig {

    protected final Environment env;

    public BasePersistenceConfig(Environment env) {
        this.env = env;
    }

    protected Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
                setProperty("hibernate.jdbc.fetch_size", env.getProperty("hibernate.jdbc.fetch_size"));
                setProperty("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
            }
        };
    }

}
