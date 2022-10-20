package com.example.appprojectbank.Repository;

import com.example.appprojectbank.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees,Long> {
}
