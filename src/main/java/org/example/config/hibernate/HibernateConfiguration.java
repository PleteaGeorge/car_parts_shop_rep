package org.example.config.hibernate;

import org.example.config.hibernate.pwd.SetPwd;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
  static {
    configuration = SetPwd.getConfigurationWithPwdProperty(
      new Configuration().configure("hibernate.cfg.xml")
    );
  }

  private final static Configuration configuration;

  public static SessionFactory getSessionFactory(Class<?>... annotatedClasses) {
    for (final Class<?> annotatedClass : annotatedClasses) {
      configuration.addAnnotatedClass(annotatedClass);
    }
    return configuration.buildSessionFactory();
  }
}
