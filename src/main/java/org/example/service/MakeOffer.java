package org.example.service;

import org.example.repository.entities.CarPart;
import org.example.repository.entities.Client;
import org.example.utils.GetCarPartsListByCrtNoList;
import org.example.utils.GetClientByCrtNo;
import org.hibernate.Session;

import java.util.List;

public class MakeOffer {
  public static void execute(Session session) {
    // choose the client (show clients) by crt.no.
    Client client = GetClientByCrtNo.execute(session);
    // choose car parts (show car parts) by ',' separated list of crt.no.
    List<CarPart> carParts = GetCarPartsListByCrtNoList.execute(session);
    // show total amount

    // choose 'by amount ($)' or 'by discount (%)'
    // choose amount/discount with respect to previous action
    // show the offer
    // choose to submit, modify or abort
  }
}
