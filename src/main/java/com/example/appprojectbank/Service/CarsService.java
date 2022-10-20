package com.example.appprojectbank.Service;

import com.example.appprojectbank.Entity.Cars;
import com.example.appprojectbank.Payload.ApiResponse;
import com.example.appprojectbank.Payload.ReqCars;
import com.example.appprojectbank.Payload.ResCars;
import com.example.appprojectbank.Repository.CarsRepository;
import com.example.appprojectbank.Repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarsService implements CarsServiceInterface {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    @Override
    public HttpEntity<?> getAllCars() {
        List<ResCars> collect = carsRepository.findAll().stream().map(ResCars::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @Override
    public ResCars getOneCar(Long id) {
        Optional<Cars> byId = carsRepository.findById(id);
        if (byId.isPresent()) {
            Cars cars = byId.get();
            return new ResCars(cars);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }

    @Override
    public ApiResponse editAndCreateCar(ReqCars reqCars) {
        try {
          Cars cars = new Cars();
            if (reqCars.getCarId() != null) {
                carsRepository.findById(reqCars.getCarId()).orElseThrow(() ->
                        new IllegalStateException("Departament with this id not found"));
            }
            cars.setCarName(reqCars.getCarName());
            cars.setCarNumber(reqCars.getCarNumber());
            if(reqCars.getEmployeeId()!=null){
                cars.setEmployee(employeesRepository.findById(reqCars.getEmployeeId()).orElseThrow(() -> new IllegalStateException("de")));
            }
             carsRepository.save(cars);
            return new ApiResponse(reqCars.getCarId() != null ? "Edit" : "Save", true);
        } catch (Exception exception) {
            return new ApiResponse("Id not found!!!!!", false);
        }
    }

    @Override
    public ApiResponse deleteCar(Long id) {
        Optional<Cars> byId = carsRepository.findById(id);
        if (byId.isPresent()) {
            carsRepository.deleteById(id);
            return new ApiResponse("Film o`chirildi",true);
        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "id not found!!!!!!!!");
        }
    }
}
