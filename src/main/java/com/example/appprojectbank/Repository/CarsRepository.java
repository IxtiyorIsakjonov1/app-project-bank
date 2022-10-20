package com.example.appprojectbank.Repository;

import com.example.appprojectbank.Entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars,Long> {
}
