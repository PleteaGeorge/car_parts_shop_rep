package org.example.config.hibernate;

import lombok.Getter;
import org.example.config.hibernate.pwd.SetPwd;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
  static {
    CONFIGURATION = SetPwd.getConfigurationWithPwdProperty(
      new Configuration().configure("hibernate.cfg.xml")
    );
  }

  @Getter
  private static final Configuration CONFIGURATION;

  public static void addAnnotatedClasses(Class<?>... annotatedClasses) {
    for (final Class<?> annotatedClass : annotatedClasses) {
      CONFIGURATION.addAnnotatedClass(annotatedClass);
    }
  }
}
