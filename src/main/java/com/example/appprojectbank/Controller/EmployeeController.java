package com.example.appprojectbank.Controller;

import com.example.appprojectbank.Payload.ReqCars;
import com.example.appprojectbank.Payload.ReqEmployees;
import com.example.appprojectbank.Service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeesService employeesService;
    @GetMapping()
    public HttpEntity<?> getAllEmployees(){
        return ResponseEntity.ok().body(employeesService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneEmployee(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(employeesService.getOneEmployee(id));
    }

    @PostMapping()
    public HttpEntity<?> EditAndCreateEmployee(@RequestBody ReqEmployees reqEmployees) {
        return ResponseEntity.ok().body(employeesService.editAndCreateEmployee(reqEmployees));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeesService.deleteEmployee(id));
    }
}
