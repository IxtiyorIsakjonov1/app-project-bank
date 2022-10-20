package com.example.appprojectbank.Service;

import com.example.appprojectbank.Payload.*;
import org.springframework.http.HttpEntity;

public interface EmployeesServiceInterface {
    public HttpEntity<?> getAllEmployees();
    public ResEmployees getOneEmployee(Long id);
    public ApiResponse editAndCreateEmployee(ReqEmployees reqEmployees);
    public ApiResponse deleteEmployee(Long id);
}
