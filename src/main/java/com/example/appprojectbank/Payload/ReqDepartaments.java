package com.example.appprojectbank.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqDepartaments {

    private Long departamentId;

    private String departamentName;
}
