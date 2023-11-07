package com.example.demo5.Repository;

import com.example.demo5.Entity.ReceiveCar;
import com.example.demo5.Entity.RepairCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepairCarRepository extends JpaRepository<RepairCar, String> {

    Optional<RepairCar> findByIdReceive(String id);

//    @Modifying
//    @Query(value = "UPDATE repair_car SET active = true WHERE id = :id", nativeQuery = true)
//    void updateActiveToTrue(@Param("id") String id);
}
