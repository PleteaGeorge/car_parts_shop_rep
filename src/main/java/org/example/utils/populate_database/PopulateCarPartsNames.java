package org.example.utils.populate_database;

import org.example.repository.CarPartNameRepository;
import org.example.repository.entities.CarPartName;
import org.hibernate.Session;

public class PopulateCarPartsNames {
  public static void deleteExisting(Session session) {
    CarPartNameRepository carPartNameRepository = new CarPartNameRepository(session);
    carPartNameRepository.findAll().stream().map(CarPartName::getId).forEach(carPartNameRepository::delete);
  }

  public static void populate(Session session) {
    CarPartNameRepository carPartNameRepository = new CarPartNameRepository(session);
    carPartNameRepository.insert(new CarPartName("driver left seat"));
    carPartNameRepository.insert(new CarPartName("driver right seat"));
    carPartNameRepository.insert(new CarPartName("passenger right seat"));
    carPartNameRepository.insert(new CarPartName("passenger left seat"));
    carPartNameRepository.insert(new CarPartName("driver left rearview mirror"));
    carPartNameRepository.insert(new CarPartName("driver right rearview mirror"));
    carPartNameRepository.insert(new CarPartName("passenger right rearview mirror"));
    carPartNameRepository.insert(new CarPartName("passenger left rearview mirror"));
    carPartNameRepository.insert(new CarPartName("inside rearview mirror"));
    carPartNameRepository.insert(new CarPartName("wheel"));
  }
}
