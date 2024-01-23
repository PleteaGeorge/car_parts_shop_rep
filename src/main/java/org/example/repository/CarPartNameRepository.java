package org.example.repository;

import org.example.repository.entities.CarPartName;
import org.hibernate.Session;

public class CarPartNameRepository extends Repository<CarPartName> {
  public CarPartNameRepository(Session session) {
    super(session, CarPartName.class);
  }
}