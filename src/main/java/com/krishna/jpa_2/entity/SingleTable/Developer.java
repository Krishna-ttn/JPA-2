package com.krishna.jpa_2.entity.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Developer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Developer extends EmployeeType{
    private String programmingLanguage;
}
