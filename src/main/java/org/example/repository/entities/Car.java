package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "cars")
public class Car {
  @Id
  @GeneratedValue
  private UUID id;
  @NonNull
  private String brand;
  @NonNull
  private String model;
  @NonNull
  private Integer manufactureYear;
  @OneToMany
  @JoinColumn(name = "car_id")
  private final List<CarPart> components = new ArrayList<>();
}
