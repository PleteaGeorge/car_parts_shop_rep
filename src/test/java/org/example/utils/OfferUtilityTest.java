package org.example.utils;

import org.example.repository.entities.CarPart;
import org.example.repository.entities.Offer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OfferUtilityTest {
  @Test
  public void getTotalAmountTest() {
    List<CarPart> carParts = List.of(
      new CarPart("555-A301-72C", 12.33, 3),
      new CarPart("7G23-X1309-AC", 12.44, 13),
      new CarPart("165721", 33.12, 31),
      new CarPart("66891-00540", 112.27, 17)
    );
    Double totalAmount = OfferUtility.getTotalAmount(carParts);
    assertEquals(170.16, totalAmount, 1e-15);
  }

  @Test
  public void getDiscountTest() {
    Offer offer = new Offer(150.5916);
    offer.getCarParts().addAll(List.of(
      new CarPart("555-A301-72C", 12.33, 3),
      new CarPart("7G23-X1309-AC", 12.44, 13),
      new CarPart("165721", 33.12, 31),
      new CarPart("66891-00540", 112.27, 17)
    ));
    Double discount = OfferUtility.getDiscount(offer);
    assertEquals(11.5, discount, 1e-15);
  }
}
