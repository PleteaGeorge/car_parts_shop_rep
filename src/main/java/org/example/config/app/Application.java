package org.example.config.app;

import lombok.Getter;
import org.example.config.hibernate.HibernateCfg;
import org.example.repository.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Application {
  private static Application instance;
  private final SessionFactory sessionFactory;
  @Getter
  private final Session session;

  private Application() {
    sessionFactory = HibernateCfg.getSessionFactory(
      Car.class,
      CarPartName.class,
      CarPart.class,
      Client.class,
      Offer.class
    );
    session = sessionFactory.openSession();
  }

  public static Application get() {
    if (null == instance) {
      synchronized (Application.class) {
        if (null == instance) {
          instance = new Application();
        }
      }
    }
    return instance;
  }

  public static void start() {
    get();
  }

  public static void terminate() {
    get().session.close();
    get().sessionFactory.close();
  }
}
