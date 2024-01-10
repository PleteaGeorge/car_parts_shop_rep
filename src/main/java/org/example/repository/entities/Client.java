package org.example.repository.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
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
    @NonNull
    private String marca;
    @OneToMany
    @JoinColumn(name= "client_Id")
   private List<Offer> offerList;


}