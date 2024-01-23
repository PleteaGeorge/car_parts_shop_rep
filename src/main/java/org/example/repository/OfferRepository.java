package org.example.repository;

import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.hibernate.Session;

import static org.example.config.app.Application.getSession;

public class OfferRepository extends Repository<Offer> {
  public OfferRepository(Session session) {
    super(session, Offer.class);
  }
  public void insert(Offer offer, Client client) {
    insert(offer);
    new ClientRepository(getSession()).update(client.getId(), offer);
  }
}