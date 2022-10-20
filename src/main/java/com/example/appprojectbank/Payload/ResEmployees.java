package com.example.appprojectbank.Payload;

import com.example.appprojectbank.Entity.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResEmployees {
    private Long employeeId;


    private String firstName;

    private String lastName;

    private String birthdate;

    private String employeeType; // ishchini toyifasi lavozimi.

    public ResEmployees(Employees employees) {
        this.employeeId = employees.getEmployeeId();
        this.firstName = employees.getFirstName();
        this.lastName = employees.getLastName();
        this.birthdate = employees.getBirthdate();
        this.employeeType = employees.getEmployeeType();
    }
}
