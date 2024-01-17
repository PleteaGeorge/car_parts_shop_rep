package org.example.ui;

import org.hibernate.Session;

@FunctionalInterface
public interface PassToBackend {
  void execute(Session session);
}
