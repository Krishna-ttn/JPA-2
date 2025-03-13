package com.krishna.jpa_2.entity.Joined;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "bike")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bike extends Vehicle {
    private boolean hasCarrier;
}
