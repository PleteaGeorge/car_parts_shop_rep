package org.example.utils;

import org.example.repository.entities.CarPart;
import org.example.repository.entities.Offer;

import java.util.List;

public class OfferDiscount {
  public static Double getTotalAmount(List<CarPart> carParts) {
    return carParts.stream().map(CarPart::getPrice).reduce(.0, Double::sum);
  }

  public static Double getDiscount(Offer offer) {
    return ((int)(10000 * (1 - (offer.getAmount() / getTotalAmount(offer.getCarParts()))))) / 100.;
  }

  public static Double getAmount(Double total, Double discount) {
    return ((int)(100 * ((1 - (discount / 100)) * total))) / 100.;
  }
}
