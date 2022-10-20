package com.example.appprojectbank.Payload;

import com.example.appprojectbank.Entity.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCars {
    private Long carId;
    private String carName;  // mashina turi qanaqaligi
    private String carNumber; // mashina nomeri

    private Employees employees;
    private Long employeeId;
}
