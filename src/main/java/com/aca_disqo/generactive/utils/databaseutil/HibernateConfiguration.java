package com.aca_disqo.generactive.utils.databaseutil;

import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {

    private final SessionFactory factory;
    private static HibernateConfiguration sInstance;

    public static HibernateConfiguration getInstance() {
        if (sInstance == null) {
            sInstance = new HibernateConfiguration();
        }
        return sInstance;
    }

    private HibernateConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addProperties(DatabaseConfigurationUtil
                .readProperties("hibernate.properties"));
        addAnnotatedClasses(configuration);
        factory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    private void addAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Group.class);
    }
}