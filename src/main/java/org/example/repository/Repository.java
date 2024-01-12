package org.example.repository;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {
  void insert(Object object);

  void delete(UUID id);

  T find(UUID id);

  List<T> findAll();
}
