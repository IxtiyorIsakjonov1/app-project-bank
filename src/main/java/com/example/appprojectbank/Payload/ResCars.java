package com.example.appprojectbank.Payload;

import com.example.appprojectbank.Entity.Cars;
import com.example.appprojectbank.Entity.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCars {
    private Long carId;
    private String carName;  // mashina turi qanaqaligi
    private String carNumber; // mashina nomeri


    public ResCars(Cars cars) {
        this.carId = cars.getCarId();
        this.carName = cars.getCarName();
        this.carNumber = cars.getCarNumber();
    }
}
