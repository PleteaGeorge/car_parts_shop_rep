package org.example;

import lombok.Getter;
import org.example.config.app.ApplicationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {
  static {
    INSTANCE = new Application();
  }

  @Getter
  private static final Application INSTANCE;
  private final SessionFactory sessionFactory;
  private final Session session;

  private Application() {
    Configuration configuration = ApplicationConfiguration.getHibernateConfiguration();
    sessionFactory = configuration.buildSessionFactory();
    session = sessionFactory.openSession();
  }

  public static SessionFactory getSessionFactory() {
    return INSTANCE.sessionFactory;
  }

  public static Session getSession() {
    return INSTANCE.session;
  }

  public static void start() {
  }

  public static void terminate() {
    INSTANCE.session.close();
    INSTANCE.sessionFactory.close();
  }
}
