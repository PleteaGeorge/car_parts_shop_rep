package org.example.repository;

import org.example.repository.entities.Car;
import org.hibernate.Session;

public class CarRepository extends Repository<Car> {
  public CarRepository(Session session) {
    super(session, Car.class);
  }
}
