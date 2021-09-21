package com.aca_disqo.generactive.utils.databaseutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static final String HIBERNATE_PROPERTIES_FILE = "hibernate.properties";

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(HIBERNATE_PROPERTIES_FILE)
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
