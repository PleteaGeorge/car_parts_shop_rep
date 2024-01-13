package org.example.repository;

import org.example.repository.entities.Car;
import org.example.repository.entities.CarPart;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class CarRepository extends Repository<Car> {
  public CarRepository(Session session) {
    super(session, Car.class);
  }

  public void update(UUID id, CarPart component) {
    Transaction transaction = null;
    try {
      transaction = getSession().beginTransaction();
      Car car = find(id);
      car.getComponents().add(component);
      getSession().persist(car);
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
