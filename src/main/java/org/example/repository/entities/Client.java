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
@Entity(name = "clients")

public class Client {
    @Id
    @GeneratedValue

    private UUID id;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String name;
    @OneToMany
    @JoinColumn(name= "client_id")
   private final List<Offer> offerList= new ArrayList<>();


}