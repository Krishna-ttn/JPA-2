package com.krishna.jpa_2.embedded;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    private double basicSalary;
    private double bonusSalary;
    private double taxAmount;
    private double specialAllowanceSalary;
}