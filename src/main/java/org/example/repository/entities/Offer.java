package org.example.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
  @Column(name = "client_id")
  private UUID clientId;
  @NonNull
  @Column(name = "car_parts_id")
  private UUID carPartsId;
  @NonNull
  private Double amount;
}
