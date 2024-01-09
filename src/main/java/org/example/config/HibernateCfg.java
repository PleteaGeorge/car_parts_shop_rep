package org.example.config;

import org.example.config.hibernate.pwd.my_pwd.SetPwd;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateCfg {
  static {
    configuration = SetPwd.getConfigurationWithPwdProperty(
      new Configuration().configure("hibernate.cfg.xml")
    );
  }

  private final static Configuration configuration;

  private static Configuration getConfiguration() {
    return configuration;
  }

  public static SessionFactory getSessionFactory() {
    return configuration.buildSessionFactory();
  }

  public static SessionFactory getSessionFactory(Class<?>... annotatedClasses) {
    for (Class<?> annotatedClass : annotatedClasses) {
      configuration.addAnnotatedClass(annotatedClass);
    }
    return configuration.buildSessionFactory();
  }
}
