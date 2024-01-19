package org.example.service;

import org.example.repository.CarPartRepository;
import org.example.repository.ClientRepository;
import org.example.repository.OfferRepository;
import org.example.repository.entities.CarPart;
import org.example.repository.entities.Offer;
import org.example.ui.UserConsole;
import org.example.utils.OfferUtility;
import org.example.utils.printed_entities_format.PrintedEntitiesFormat;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OfferService {
  private static final String SET_BY_AMOUNT = "$";
  private static final String SET_BY_DISCOUNT = "%";
  private static final String SUBMIT = "S";
  private static final String MODIFY = "M";
  private static final String ABORT = "A";
  private final OfferRepository offerRepository;

  public OfferService(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  public void makeOffer() {
    Offer offer = new Offer(.0);
    while (true) {
      // choose the client (show clients) by crt.no.
      offer.setClient(new ClientService(new ClientRepository(offerRepository.getSession()))
        .getClientByCrtNo()
      );
      // choose car parts (show car parts) by ',' separated list of crt.no.
      offer.getCarParts().addAll(new CarPartService(new CarPartRepository(offerRepository.getSession()))
        .getCarPartsByCrtNoList()
      );
      // show total amount
      System.out.printf("Total amount: $%.2f\n", OfferUtility.getTotalAmount(offer.getCarParts()));
      // choose 'by amount ($)' or 'by discount (%)'
      System.out.print("Set the offer by amount or by discount ? ( "
        + SET_BY_AMOUNT + " / [" + SET_BY_DISCOUNT + "] ): "
      );
      boolean byAmount = UserConsole.getInstance().getScanner().nextLine().trim().equals(SET_BY_AMOUNT);
      // choose amount/discount with respect to previous action
      System.out.print("Set " + (byAmount ? "amount ( $ )" : "discount ( % )") + ": ");
      offer.setAmount(Double.parseDouble(UserConsole.getInstance().getScanner().nextLine()));
      if (!byAmount) {
        offer.setAmount(OfferUtility.getAmount(
          OfferUtility.getTotalAmount(offer.getCarParts()), offer.getAmount()
        ));
      }
      // show the offer
      System.out.println(getPrintedWithClient(offer));
      // choose to submit, modify or abort
      System.out.print("Submit, Modify, Abort ? ( " + SUBMIT + " / [" + MODIFY + "] / " + ABORT + " ): ");
      String answer = UserConsole.getInstance().getScanner().nextLine().trim();
      if (ABORT.equalsIgnoreCase(answer)) {
        break;
      }
      if (SUBMIT.equalsIgnoreCase(answer)) {
//        offer.setDate(Date.from(Instant.now()));
        offerRepository.insert(offer);
        break;
      }
      offer.getCarParts().clear();
    }
  }

  public String getPrinted(Offer offer, int numberOfSpacesToIndent) {
    UUID id = offer.getId();
    List<CarPart> carParts = offer.getCarParts();
    String indent = PrintedEntitiesFormat.getIndent(numberOfSpacesToIndent);
    StringBuilder result = new StringBuilder(indent
      + "Offer {" + ((null == id) ? "not yet an offer" : id.toString()) + "}:"
      + PrintedEntitiesFormat.END_LINE
    );
    carParts.forEach(
      carPart -> result.append(indent)
        .append("    ").append(carPart.toString())
        .append(PrintedEntitiesFormat.END_LINE)
    );
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    return result.append(indent)
      .append("  total amount: $").append(decimalFormat.format(OfferUtility.getTotalAmount(carParts)))
      .append("  amount: $").append(decimalFormat.format(offer.getAmount()))
      .append("  discount: ").append(decimalFormat.format(OfferUtility.getDiscount(offer))).append(" %")
      .toString();
  }

  public String getPrintedWithClient(Offer offer) {
    return "Client: " + offer.getClient() + PrintedEntitiesFormat.END_LINE
      + getPrinted(offer, 0);
  }
}
