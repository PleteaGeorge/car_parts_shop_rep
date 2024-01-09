package org.example;

import org.example.config.HibernateCfg;
import org.example.repository.entities.CarPart;
import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
  public static void main(String[] args) {
    try (
      SessionFactory sessionFactory = HibernateCfg.getSessionFactory(
        CarPart.class,
        Client.class,
        Offer.class
      );
      Session session = sessionFactory.openSession();
    ) {
    }
  }
}
