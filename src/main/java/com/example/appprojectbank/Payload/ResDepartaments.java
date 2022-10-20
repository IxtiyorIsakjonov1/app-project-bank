package com.example.appprojectbank.Payload;

import com.example.appprojectbank.Entity.Departaments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDepartaments {

    private Long departamentId;

    private String departamentName;

    public ResDepartaments(Departaments departaments) {
        this.departamentId = departaments.getDepartamentId();
        this.departamentName = departaments.getDepartamentName();
    }
}
