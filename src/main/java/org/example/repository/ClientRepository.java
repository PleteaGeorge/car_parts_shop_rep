package org.example.repository;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.example.repository.entities.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ClientRepository implements Repository<Client> {
  private final static String CLIENTS = Client.class.getAnnotation(Entity.class).name();
  private final Session session;

  @Override
  public void insert(Object client) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(client);
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }

  @Override
  public void delete(UUID id) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.remove(session.get(Client.class, id));
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }

  @Override
  public Client find(UUID id) {
    return session.get(Client.class, id);
  }

  @Override
  public List<Client> findAll() {
    return session.createQuery("FROM " + CLIENTS, Client.class)
      .getResultList();
  }
}
