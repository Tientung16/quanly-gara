package com.example.demo5.Repository;

import com.example.demo5.Entity.Customer;
import com.example.demo5.Entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query("select a from Vehicle a where a.licensePlates like %:licensePlates%")
    Page<Vehicle> findVehicleByLicensePlates(Pageable pageable, String licensePlates);
}
