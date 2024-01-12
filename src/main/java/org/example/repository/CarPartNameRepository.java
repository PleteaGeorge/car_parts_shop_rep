package org.example.repository;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.example.repository.entities.CarPartName;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CarPartNameRepository implements Repository<CarPartName> {
  private final static String CAR_PARTS_NAMES = CarPartName.class.getAnnotation(Entity.class).name();
  private final Session session;

  @Override
  public void insert(Object carPartName) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(carPartName);
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
      session.remove(session.get(CarPartName.class, id));
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
  public CarPartName find(UUID id) {
    return session.get(CarPartName.class, id);
  }

  @Override
  public List<CarPartName> findAll() {
    return session.createQuery("FROM " + CAR_PARTS_NAMES, CarPartName.class)
      .getResultList();
  }

  public void update(UUID id, String name) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      CarPartName carPartName = find(id);
      carPartName.setName(name);
      session.persist(carPartName);
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }
}
