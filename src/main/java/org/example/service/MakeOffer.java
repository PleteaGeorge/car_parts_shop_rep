package org.example.service;

import org.example.repository.OfferRepository;
import org.example.repository.entities.CarPart;
import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.example.ui.UserConsole;
import org.example.utils.GetCarPartsListByCrtNoList;
import org.example.utils.GetClientByCrtNo;
import org.example.utils.OfferUtility;
import org.hibernate.Session;

import java.util.List;

public class MakeOffer {
  public static void execute(Session session) {
    Offer offer = new Offer(.0);
    while (true) {
      // choose the client (show clients) by crt.no.
      Client client = GetClientByCrtNo.execute(session);
      // choose car parts (show car parts) by ',' separated list of crt.no.
      List<CarPart> carParts = GetCarPartsListByCrtNoList.execute(session);
      // show total amount
      System.out.printf("Total amount: $%.2f\n", OfferUtility.getTotalAmount(carParts));
      // choose 'by amount ($)' or 'by discount (%)'
      System.out.print("Set the offer by amount or by discount ? ( $ / [%] ): ");
      boolean byAmount = UserConsole.get().getScanner().nextLine().trim().equals("$");
      // choose amount/discount with respect to previous action
      System.out.print("Set " + (byAmount ? "amount ( $ )" : "discount ( % )") + ": ");
      Double tmp = Double.parseDouble(UserConsole.get().getScanner().nextLine());
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
      System.out.print("Submit, Modify, Abort ? ( S / [M] / A ): ");
      String answer = UserConsole.get().getScanner().nextLine().trim();
      if ("A".equalsIgnoreCase(answer)) {
        break;
      }
      if ("S".equalsIgnoreCase(answer)) {
        new OfferRepository(session).insert(offer, client);
        break;
      }
      offer.getCarParts().clear();
    }
  }
}
