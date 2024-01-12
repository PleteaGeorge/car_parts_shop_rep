package org.example.repository;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.example.repository.entities.CarPart;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CarPartRepository implements Repository<CarPart> {
  private final static String CAR_PARTS = CarPart.class.getAnnotation(Entity.class).name();
  private final Session session;

  @Override
  public void insert(Object carPart) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(carPart);
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
      session.remove(session.get(CarPart.class, id));
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
  public CarPart find(UUID id) {
    return session.get(CarPart.class, id);
  }

  @Override
  public List<CarPart> findAll() {
    return session.createQuery("FROM " + CAR_PARTS, CarPart.class)
      .getResultList();
  }
}
