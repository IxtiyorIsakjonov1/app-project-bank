package com.example.appprojectbank.Service;

import com.example.appprojectbank.Entity.Employees;
import com.example.appprojectbank.Payload.ApiResponse;
import com.example.appprojectbank.Payload.ReqEmployees;
import com.example.appprojectbank.Payload.ResEmployees;
import com.example.appprojectbank.Repository.DepartamentsRepository;
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
public class EmployeesService implements EmployeesServiceInterface{
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private DepartamentsRepository departamentsRepository;


    @Override
    public HttpEntity<?> getAllEmployees() {
        List<ResEmployees> collect = employeesRepository.findAll().stream().map(ResEmployees::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @Override
    public ResEmployees getOneEmployee(Long id) {
        Optional<Employees> byId = employeesRepository.findById(id);
        if (byId.isPresent()) {
            Employees employees = byId.get();
            return new ResEmployees(employees);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Id not found!!!!!!");
        }
    }

    @Override
    public ApiResponse editAndCreateEmployee(ReqEmployees reqEmployees) {
        try {
            Employees employees = new Employees();
            if (reqEmployees.getEmployeeId() != null) {
                employeesRepository.findById(reqEmployees.getEmployeeId()).orElseThrow(() ->
                        new IllegalStateException("Departament with this id not found"));
            }

            employees.setFirstName(reqEmployees.getFirstName());
            employees.setLastName(reqEmployees.getLastName());
            employees.setBirthdate(reqEmployees.getBirthdate());
            employees.setEmployeeType(reqEmployees.getEmployeeType());
            if(reqEmployees.getDepartamentId()!=null){
                employees.setDepartament(departamentsRepository.findById(reqEmployees.getDepartamentId()).orElseThrow(() -> new IllegalStateException("de")));
            }
            employeesRepository.save(employees);
            return new ApiResponse(reqEmployees.getEmployeeId() != null ? "Edit" : "Save", true);
        } catch (Exception exception) {
            return new ApiResponse("Id not found!!!!!", false);
        }
    }

    @Override
    public ApiResponse deleteEmployee(Long id) {
        Optional<Employees> byId = employeesRepository.findById(id);
        if (byId.isPresent()) {
            employeesRepository.deleteById(id);
            return new ApiResponse("Film o`chirildi",true);
        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "id not found!!!!!!!!");
        }
    }
}
