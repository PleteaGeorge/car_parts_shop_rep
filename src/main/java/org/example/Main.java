package org.example;

import org.example.config.hibernate.HibernateCfg;
import org.example.repository.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateCfg.getSessionFactory(
                CarPart.class,
                Offer.class,
                Client.class,
                Car.class,
                CarPartName.class
            );
             Session session = sessionFactory.openSession();
        ){
        }
    }
}
