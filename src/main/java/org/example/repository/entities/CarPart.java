package org.example.repository.entities;

import jakarta.persistence.*;
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
    private String oeNumber;
    @NonNull
    private Double price;
    @NonNull
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "car_parts_names_id")
    private CarPartName name;
}
