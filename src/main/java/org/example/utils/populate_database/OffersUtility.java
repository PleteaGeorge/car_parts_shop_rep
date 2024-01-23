package org.example.utils.populate_database;

import org.example.repository.OfferRepository;
import org.example.repository.entities.Offer;
import org.hibernate.Session;

public class OffersUtility {
  public static void deleteExisting(Session session) {
    OfferRepository offerRepository = new OfferRepository(session);
    offerRepository.findAll().stream().map(Offer::getId).forEach(offerRepository::delete);
  }
}