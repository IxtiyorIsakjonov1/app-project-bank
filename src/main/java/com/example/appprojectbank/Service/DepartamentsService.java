package com.example.appprojectbank.Service;

import com.example.appprojectbank.Entity.Departaments;
import com.example.appprojectbank.Payload.ApiResponse;
import com.example.appprojectbank.Payload.ReqDepartaments;
import com.example.appprojectbank.Payload.ResDepartaments;
import com.example.appprojectbank.Repository.DepartamentsRepository;
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
public class DepartamentsService implements DepartamentsServiceInterface {
    @Autowired
    private DepartamentsRepository deportamentsRepository;


    @Override
    public HttpEntity<?> getAllDepartaments() {
        List<ResDepartaments> collect = deportamentsRepository.findAll().stream().map(ResDepartaments::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @Override
    public ResDepartaments getOneDepartament(Long id) {
        Optional<Departaments> byId = deportamentsRepository.findById(id);
        if (byId.isPresent()) {
            Departaments departaments = byId.get();
            return new ResDepartaments(departaments);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Id not found!!!!!!!!");
        }
    }

    @Override
    public ApiResponse editAndCreateDepartament(ReqDepartaments reqDepartaments) {
        try {
            Departaments departaments = new Departaments();
            if (reqDepartaments.getDepartamentId() != null) {
                deportamentsRepository.findById(reqDepartaments.getDepartamentId()).orElseThrow(() ->
                        new IllegalStateException("Departament with this id not found"));
            }
            departaments.setDepartamentId(reqDepartaments.getDepartamentId());
            departaments.setDepartamentName(reqDepartaments.getDepartamentName());
            deportamentsRepository.save(departaments);
            return new ApiResponse(reqDepartaments.getDepartamentId() != null ? "Edit" : "Save", true);
        } catch (Exception exception) {
            return new ApiResponse("Id not found!!!!!", false);
        }
    }

    @Override
    public ApiResponse deleteDepartament(Long id) {
        Optional<Departaments> byId = deportamentsRepository.findById(id);
        if (byId.isPresent()) {
            deportamentsRepository.deleteById(id);
            return new ApiResponse("Film o`chirildi",true);
        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "id not found!!!!!!!!");
        }
    }
}
