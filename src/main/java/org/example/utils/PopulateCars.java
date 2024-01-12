package org.example.utils;

import org.example.config.app.Application;
import org.example.repository.CarRepository;
import org.example.repository.entities.Car;
import org.hibernate.Session;

public class PopulateCars {
  public static void main(String[] args) {
    Application.start();
    deleteExisting(Application.getSession());
    populate(Application.getSession());
    Application.terminate();
  }

  public static void deleteExisting(Session session) {
    CarRepository carRepository = new CarRepository(session);
    carRepository.findAll().stream().map(Car::getId).forEach(carRepository::delete);
  }

  public static void populate(Session session) {
    CarRepository carRepository = new CarRepository(session);
    carRepository.insert(new Car("Ford", "Mondeo", 2010));
    carRepository.insert(new Car("Ford", "Focus", 2014));
    carRepository.insert(new Car("Fiat", "Stilo", 2002));
    carRepository.insert(new Car("BMW", "E46", 2003));
    carRepository.insert(new Car("Skoda", "Octavia", 2015));
    carRepository.insert(new Car("Fiat", "Grande Punto", 2006));
    carRepository.insert(new Car("Dacia", "Logan", 2021));
    carRepository.insert(new Car("Skoda", "Superb", 2009));
    carRepository.insert(new Car("Smart", "ForTwo", 2008));
    carRepository.insert(new Car("Opel", "Insignia", 2011));
  }
}
