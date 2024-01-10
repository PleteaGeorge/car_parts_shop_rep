package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany
    @JoinColumn(name = "client_Id")
    private List<Offer> offerList;

}
