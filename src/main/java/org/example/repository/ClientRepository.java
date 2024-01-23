package org.example.repository;

import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

import static org.example.config.app.Application.getSession;

public class ClientRepository extends Repository<Client> {
  public ClientRepository(Session session) {
    super(session, Client.class);
  }
  public void update(UUID id, Offer offer) {
    Transaction transaction = null;
    try {
      transaction = getSession().beginTransaction();
      Client client = find(id);
      client.getOffers().add(offer);
      getSession().persist(client);
      transaction.commit();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      if (null != transaction) {
        transaction.rollback();
      }
    }
  }
}