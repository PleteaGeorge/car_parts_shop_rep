package org.example.repository;

import org.example.repository.entities.CarPartName;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class CarPartNameRepository extends Repository<CarPartName> {
  public CarPartNameRepository(Session session) {
    super(session, CarPartName.class);
  }

  public void update(UUID id, String name) {
    Transaction transaction = null;
    try {
      transaction = getSession().beginTransaction();
      CarPartName carPartName = find(id);
      carPartName.setName(name);
      getSession().persist(carPartName);
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
