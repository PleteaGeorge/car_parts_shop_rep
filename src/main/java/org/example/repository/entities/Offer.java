package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.utils.OfferDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "offers")
public class Offer {
  @Id
  @GeneratedValue
  private UUID id;
  @NonNull
  private Double amount;
  @ManyToMany
  @JoinTable(
    name = "car_parts_to_offers",
    joinColumns = @JoinColumn(name = "offer_id"),
    inverseJoinColumns = @JoinColumn(name = "car_part_id")
  )
  private final List<CarPart> carParts = new ArrayList<>();

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("Offer {" + id.toString() + "}:\n");
    carParts.forEach(carPart -> result.append("    ").append(carPart.toString()).append("\n"));
    return result
      .append("  total amount: $").append(OfferDiscount.getTotalAmount(this))
      .append("  amount: $").append(amount.toString())
      .append("  discount: ").append(OfferDiscount.getDiscount(this)).append(" %\n")
      .toString();
  }
}
