package org.example.service;

import org.example.repository.OfferRepository;
import org.example.repository.entities.CarPart;
import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.example.ui.UserConsole;
import org.example.utils.OfferUtility;
import org.hibernate.Session;

import java.util.List;

public class OfferService {
  private static final String SET_BY_AMOUNT = "$";
  private static final String SET_BY_DISCOUNT = "%";
  private static final String SUBMIT = "S";
  private static final String MODIFY = "M";
  private static final String ABORT = "A";
  private final OfferRepository offerRepository;
  private final Session session;

  public OfferService(Session session) {
    this.session = session;
    offerRepository = new OfferRepository(session);
  }

  public void makeOffer() {
    Offer offer = new Offer(.0);
    while (true) {
      // choose the client (show clients) by crt.no.
      Client client = new ClientService(session).getClientByCrtNo();
      // choose car parts (show car parts) by ',' separated list of crt.no.
      List<CarPart> carParts = new CarPartService(session).getCarPartsByCrtNoList();
      // show total amount
      System.out.printf("Total amount: $%.2f\n", OfferUtility.getTotalAmount(carParts));
      // choose 'by amount ($)' or 'by discount (%)'
      System.out.print("Set the offer by amount or by discount ? ( " + SET_BY_AMOUNT + " / [" + SET_BY_DISCOUNT + "] ): ");
      boolean byAmount = UserConsole.getInstance().getScanner().nextLine().trim().equals(SET_BY_AMOUNT);
      // choose amount/discount with respect to previous action
      System.out.print("Set " + (byAmount ? "amount ( $ )" : "discount ( % )") + ": ");
      Double tmp = Double.parseDouble(UserConsole.getInstance().getScanner().nextLine());
      if (!byAmount) {
        tmp = OfferUtility.getAmount(OfferUtility.getTotalAmount(carParts), tmp);
      }
      // fill the offer
      offer.setAmount(tmp);
      offer.getCarParts().addAll(carParts);
      // show the offer
      System.out.println("Client: " + client);
      System.out.println(offer);
      // choose to submit, modify or abort
      System.out.print("Submit, Modify, Abort ? ( " + SUBMIT + " / [" + MODIFY + "] / " + ABORT + " ): ");
      String answer = UserConsole.getInstance().getScanner().nextLine().trim();
      if (ABORT.equalsIgnoreCase(answer)) {
        break;
      }
      if (SUBMIT.equalsIgnoreCase(answer)) {
        offerRepository.insert(offer, client);
        break;
      }
      offer.getCarParts().clear();
    }
  }
}
