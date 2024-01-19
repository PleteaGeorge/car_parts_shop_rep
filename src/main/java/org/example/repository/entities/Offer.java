package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;
}
