package org.example.service;

import org.example.repository.CarPartRepository;
import org.example.repository.entities.CarPart;
import org.example.ui.UserConsole;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarPartService {
  private final CarPartRepository carPartRepository;
  private final Session session;

  public CarPartService(Session session) {
    this.session = session;
    carPartRepository = new CarPartRepository(session);
  }

  public List<CarPart> getCarPartsByCrtNoList() {
    Map<Integer, CarPart> map = new HashMap<>();
    int i = 0;
    for (CarPart carPart : carPartRepository.findAll()) {
      map.put(++i, carPart);
      System.out.printf("%5d. %s\n", i, carPart.toString());
    }
    System.out.print("Choose car parts by ',' separated list of current numbers: ");
    return Arrays.stream(UserConsole.getInstance().getScanner().nextLine().replaceAll("\\s", "").split(","))
      .map(Integer::parseInt).map(map::get).toList();
  }
}
