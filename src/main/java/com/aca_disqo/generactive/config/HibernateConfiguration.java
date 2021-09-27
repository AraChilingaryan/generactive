package com.aca_disqo.generactive.config;

import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.utils.DatabaseConfigurationUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HibernateConfiguration {


    @Bean
    public SessionFactory sessionFactory() {
        return configure().buildSessionFactory();
    }

    public org.hibernate.cfg.Configuration configure() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addProperties(hibernateProperties());
        addAnnotatedClasses(configuration);

        return configuration;
    }

    public Properties hibernateProperties() {
        return DatabaseConfigurationUtil
                .readProperties("hibernate.properties");
    }

    private void addAnnotatedClasses(org.hibernate.cfg.Configuration configuration) {
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Group.class);
    }
}