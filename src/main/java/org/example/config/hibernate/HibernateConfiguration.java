package org.example.config.hibernate;

import org.example.config.hibernate.pwd.my_pwd.SetPwd;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
    static {
        configuration = SetPwd.getConfigurationWithProperty(
                new Configuration().configure("hibernate.cfg.xml")
        );
    }

    private final static Configuration configuration;

    public static SessionFactory getSessionFactory(Class<?>... annotetedClasses) {
       for(Class<?> annotatedClass : annotetedClasses){
           configuration.addAnnotatedClass(annotatedClass);
    }
    return configuration.buildSessionFactory();
}
}
