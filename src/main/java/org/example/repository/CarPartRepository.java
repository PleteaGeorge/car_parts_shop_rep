package org.example.repository;

import org.example.repository.entities.CarPart;
import org.hibernate.Session;

public class CarPartRepository extends Repository<CarPart> {
  public CarPartRepository(Session session) {
    super(session, CarPart.class);
  }
}
