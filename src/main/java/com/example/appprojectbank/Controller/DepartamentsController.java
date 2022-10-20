package com.example.appprojectbank.Controller;

import com.example.appprojectbank.Payload.ReqDepartaments;
import com.example.appprojectbank.Service.DepartamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departament")
public class DepartamentsController {
    @Autowired
    private DepartamentsService departamentsService;
    @GetMapping()
    public HttpEntity<?> getAllDepartaments(){
        return ResponseEntity.ok().body(departamentsService.getAllDepartaments());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneDepartament(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(departamentsService.getOneDepartament(id));
    }

    @PostMapping()
    public HttpEntity<?> EditAndCreateDepartament(@RequestBody ReqDepartaments reqDepartaments) {
        return ResponseEntity.ok().body(departamentsService.editAndCreateDepartament(reqDepartaments));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDepartament(@PathVariable Long id) {
        return ResponseEntity.ok().body(departamentsService.deleteDepartament(id));
    }
}
