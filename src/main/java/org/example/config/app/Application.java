package org.example.config.app;

import org.example.config.hibernate.HibernateConfiguration;
import org.example.repository.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Application {
  private static Application instance;
  private final SessionFactory sessionFactory;
  private final Session session;

  private Application() {
    sessionFactory = HibernateConfiguration.getSessionFactory(
      Car.class,
      CarPartName.class,
      CarPart.class,
      Client.class,
      Offer.class
    );
    session = sessionFactory.openSession();
  }

  private static Application get() {
    if (null != instance) {
      return instance;
    }
    synchronized (Application.class) {
      if (null == instance) {
        instance = new Application();
      }
    }
    return instance;
  }

  public static void start() {
    get();
  }

  public static Session getSession() {
    return get().session;
  }

  public static void terminate() {
    get().session.close();
    get().sessionFactory.close();
  }
}
