package org.example.config.app;

import org.example.config.hibernate.HibernateConfiguration;
import org.example.repository.entities.*;
import org.hibernate.cfg.Configuration;

public class ApplicationConfiguration {
  public static synchronized Configuration getHibernateConfiguration() {
    HibernateConfiguration.addAnnotatedClasses(
      Car.class,
      CarPartName.class,
      CarPart.class,
      Client.class,
      Offer.class
    );
    return HibernateConfiguration.getCONFIGURATION();
  }
}
