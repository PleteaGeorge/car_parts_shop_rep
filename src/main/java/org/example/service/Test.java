package org.example.service;

import org.example.repository.CarRepository;
import org.example.repository.entities.Car;
import org.hibernate.Session;

import java.util.List;

public class Test {
  public static void execute(Session session){
    CarRepository carRepository=new CarRepository(session);
    List<Car> carsFull=carRepository.findAll();
    System.out.println(carsFull.get(0).getId());
    List<Car> cars=carRepository.test(carsFull.get(0).getId());
    System.out.println(cars.get(0).getComponents());
    System.out.println(carRepository.find(carsFull.get(0).getId()).getComponents());
  }
}
