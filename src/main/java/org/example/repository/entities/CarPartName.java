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
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name="car_parts_names")

public class CarPartName {
    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    private String name;

    public UUID getId(){
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
}
