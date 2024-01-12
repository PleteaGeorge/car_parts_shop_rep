package org.example.repository;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.example.repository.entities.Offer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class OfferRepository implements Repository<Offer> {
  private final static String OFFERS = Offer.class.getAnnotation(Entity.class).name();
  private final Session session;

  @Override
  public void insert(Object offer) {
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(offer);
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
      session.remove(session.get(Offer.class, id));
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
  public Offer find(UUID id) {
    return session.get(Offer.class, id);
  }

  @Override
  public List<Offer> findAll() {
    return session.createQuery("FROM " + OFFERS, Offer.class)
      .getResultList();
  }
}
