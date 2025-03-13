package com.krishna.jpa_2.entity.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Manager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends EmployeeType{
    private int teamSize;
}
