package org.example.config.ui;

import lombok.Getter;
import org.example.config.hibernate.HibernateCfg;
import org.example.repository.entities.CarPart;
import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class UserConsoleConfiguration {
  static {
    commands = new HashMap<>();
    buildCommands();
  }

  @Getter
  private final static Map<String, IFunctor> commands;

  private static void buildCommands() {
    Executor
    commands.put("command", new IFunctor() {
      @Override
      public Session executeProvide() {
        try (
          SessionFactory sessionFactory = HibernateCfg.getSessionFactory(
            CarPart.class,
            Client.class,
            Offer.class);
        ) {
          return sessionFactory.openSession();
        }
      }
    });
  }
}
