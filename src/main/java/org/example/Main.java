package org.example;

import org.example.config.hibernate.HibernateConfiguration;
import org.example.repository.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory(
                CarPart.class,
                Offer.class,
                Client.class,
                Car.class,
                CarPartName.class
            );
             Session session = sessionFactory.openSession()
        ){
        }
    }
}
