package org.example.repository.entities.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "car_parts")

    public class CarParts{
            @Id
            @GeneratedValue
            private UUID id;
            @NonNull
            private double price;
            @NonNull
            private String name;

}


