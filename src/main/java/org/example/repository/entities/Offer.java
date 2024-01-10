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
@Entity(name = "offers")

public class Offer {
    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    @Column(name = "client_Id")
    private UUID clientId;
    @NonNull
    @Column(name = "car_parts_id")
    private UUID carPartsID;
    @NonNull
    private Double amount;
    @ManyToMany
    @JoinTable(name = "offer_to_car_parts", joinColumns = @JoinColumn(name = "offer_Id"),
                inverseJoinColumns = @JoinColumn(name = "car_part_id"))
    private List<CarPart> carParts;

}
