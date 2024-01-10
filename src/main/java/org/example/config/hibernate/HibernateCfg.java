package org.example.config.hibernate;


import org.example.config.hibernate.pwd.my_pwd.SetPwd;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateCfg {
    static{
        configuration = SetPwd.getConfigurationWithPWDProperty(
                new Configuration().configure("hibernate.cfg.xml"));

    }
    private final static Configuration configuration;
    public static SessionFactory getSessionFactory(Class<?>... annotatedClasses){
        for (Class<?> annotatedClass : annotatedClasses){

            configuration.addAnnotatedClass(annotatedClass);
        }

            return configuration.buildSessionFactory();

    }
}
