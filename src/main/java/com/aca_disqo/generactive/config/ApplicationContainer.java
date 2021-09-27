package com.aca_disqo.generactive.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContainer {
    public static final ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("applicationContext.xml");
}
