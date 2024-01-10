package org.example.repository.entities.entites;


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
@Entity(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    private int phone;
    @NonNull
    private String name;


}
