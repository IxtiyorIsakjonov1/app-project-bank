package com.example.appprojectbank.Controller;

import com.example.appprojectbank.Payload.ReqCars;
import com.example.appprojectbank.Service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarsController {
    @Autowired
    private CarsService carsService;

    @GetMapping()
    public HttpEntity<?> getAllCars(){
        return ResponseEntity.ok().body(carsService.getAllCars());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneCar(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(carsService.getOneCar(id));
    }

    @PostMapping()
    public HttpEntity<?> EditAndCreateCar(@RequestBody ReqCars reqCars) {
        return ResponseEntity.ok().body(carsService.editAndCreateCar(reqCars));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCar(@PathVariable Long id) {
        return ResponseEntity.ok().body(carsService.deleteCar(id));
    }

}
