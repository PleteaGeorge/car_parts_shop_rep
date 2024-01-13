package org.example.utils;

import org.example.repository.entities.CarPart;
import org.example.repository.entities.Offer;

import java.util.List;

public class OfferDiscount {
  public static Double getTotalAmount(List<CarPart> carParts) {
    return carParts.stream().map(CarPart::getPrice).reduce(.0, Double::sum);
  }

  public static Double getDiscount(Offer offer) {
    if (1e-5 > offer.getAmount()) {
      return 100.;
    }
    return ((int)(10000 * (1 - (getTotalAmount(offer.getCarParts()) / offer.getAmount())))) / 100.;
  }
}
