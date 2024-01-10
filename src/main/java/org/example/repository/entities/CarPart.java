package org.example.repository.entities;

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
@Entity(name = "car_parts")

public class CarPart {
    @Id
    @GeneratedValue

    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private double price;
    @NonNull
    private String marca;

}
