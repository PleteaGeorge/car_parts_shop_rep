package org.example.repository;

import org.example.repository.entities.Offer;
import org.hibernate.Session;

public class OfferRepository extends Repository<Offer> {

    public OfferRepository(Session session){
        super(session, Offer.class);
    }
}