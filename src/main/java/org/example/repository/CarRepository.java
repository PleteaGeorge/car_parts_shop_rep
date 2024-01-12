package org.example.repository;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.example.repository.entities.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CarRepository implements Repository<Car> {
  private final static String CARS = Car.class.getAnnotation(Entity.class).name();
  private final Session session;

  @Override
  public void insert(Object car) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(car);
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }

  @Override
  public void delete(UUID id) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.remove(session.get(Car.class, id));
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }

  @Override
  public Car find(UUID id) {
    return session.get(Car.class, id);
  }

  @Override
  public List<Car> findAll() {
    return session.createQuery("FROM " + CARS, Car.class)
      .getResultList();
  }
}
