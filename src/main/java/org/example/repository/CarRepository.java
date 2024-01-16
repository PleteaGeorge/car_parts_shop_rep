package org.example.repository;

import lombok.NonNull;
import org.example.repository.entities.Car;
import org.hibernate.Session;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarRepository extends Repository<Car> {
  public CarRepository(Session session) {
    super(session, Car.class);
  }

  public List<Car> test(UUID id) {
    System.out.println(Arrays.stream(Car.class.getDeclaredFields())
//      .filter(field -> field.isAnnotationPresent(NonNull.class))
      .map(Field::getName)
      .toList());
    Optional<String> fieldsOptional =
      Arrays.stream(Car.class.getDeclaredFields())
        .filter(field -> null != field.getAnnotation(NonNull.class))
        .map(Field::getName)
        .reduce((acc, str) -> acc + ", " + str);
    final String table = "table";
    String fields = fieldsOptional.orElse(table);
    return getSession()
      .createQuery("SELECT " + fields + " FROM " + Car.class.getName() + " AS " + table, Car.class)
      .getResultList();
  }
}
