package org.example.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "car_parts")
public class CarPart {
  @Id
  @GeneratedValue
  private UUID id;
  @NonNull
  private String name;
  @NonNull
  private Double price;
  @NonNull
  private String marca;
}
