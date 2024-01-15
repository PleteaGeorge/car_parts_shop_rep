package org.example.utils;

import org.example.repository.entities.CarPart;
import org.example.repository.entities.Offer;

import java.util.List;

public class OfferUtility {
  public static Double getTotalAmount(List<CarPart> carParts) {
    return carParts.stream().map(CarPart::getPrice).reduce(.0, Double::sum);
  }

  public static Double getDiscount(Offer offer) {
    return 100 * (1 - (offer.getAmount() / getTotalAmount(offer.getCarParts())));
  }

  public static Double getAmount(Double total, Double discount) {
    return total * (1 - (discount / 100));
  }
}
