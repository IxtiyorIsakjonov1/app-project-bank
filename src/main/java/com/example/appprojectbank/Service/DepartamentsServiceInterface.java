package com.example.appprojectbank.Service;

import com.example.appprojectbank.Payload.ApiResponse;
import com.example.appprojectbank.Payload.ReqDepartaments;
import com.example.appprojectbank.Payload.ResDepartaments;
import org.springframework.http.HttpEntity;

public interface DepartamentsServiceInterface {

    public HttpEntity<?> getAllDepartaments();
    public ResDepartaments getOneDepartament(Long id);
    public ApiResponse editAndCreateDepartament(ReqDepartaments reqDepartaments);
    public ApiResponse deleteDepartament(Long id);
}
