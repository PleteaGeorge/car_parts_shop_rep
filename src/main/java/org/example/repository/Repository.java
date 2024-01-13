package org.example.repository;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public abstract class Repository<T> {
  @Getter
  private final Session session;
  private final Class<T> typeParameterClass;
  @Getter
  private final String typeParameterClassName;

  public Repository(Session session, Class<T> typeParameterClass) {
    this.session = session;
    this.typeParameterClass = typeParameterClass;
    typeParameterClassName = typeParameterClass.getName();
  }

  public void insert(Object object) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(object);
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }

  public void delete(UUID id) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.remove(session.get(typeParameterClass, id));
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }

  public T find(UUID id) {
    return session.get(typeParameterClass, id);
  }

  public List<T> findAll() {
    return session.createQuery("FROM " + typeParameterClassName, typeParameterClass)
      .getResultList();
  }
}
