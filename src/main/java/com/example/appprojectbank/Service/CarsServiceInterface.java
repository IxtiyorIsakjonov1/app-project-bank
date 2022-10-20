package com.example.appprojectbank.Service;

import com.example.appprojectbank.Payload.*;
import org.springframework.http.HttpEntity;

public interface CarsServiceInterface {
    public HttpEntity<?> getAllCars();
    public ResCars getOneCar(Long id);
    public ApiResponse editAndCreateCar(ReqCars reqCars);
    public ApiResponse deleteCar(Long id);
}
