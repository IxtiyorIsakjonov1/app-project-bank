package com.example.appprojectbank.Payload;

import com.example.appprojectbank.Entity.Departaments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqEmployees {
    private Long employeeId;


    private String firstName;

    private String lastName;

    private String birthdate;

    private String employeeType; // ishchini toyifasi lavozimi.

    private Long departamentId;
}
