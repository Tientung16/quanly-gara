package com.example.demo5.Repository;

import com.example.demo5.Entity.Customer;
import com.example.demo5.Entity.ReceiveCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReceiveCarRepository extends JpaRepository<ReceiveCar, String> {
    @Query("select a from ReceiveCar a where a.licensePlates like %:licensePlates% and a.active = :active")
    Page<ReceiveCar> findReceiveCarByLicensePlates(Pageable pageable, String licensePlates,@Param("active") Boolean active);

    @Query("select a from ReceiveCar a where a.active = :active")
    Page<ReceiveCar> findAllByActive(Boolean active,Pageable pageable );

    @Query("select a from ReceiveCar a where a.active = :active")
    List<ReceiveCar> findAllByActiveToGetPage(Boolean active);

//    @Query("UPDATE ReceiveCar c SET c.active = true WHERE c.id = :id")
//    void updateActiveToTrue(@Param("id") String id);

    @Modifying
    @Query(value = "UPDATE recieve_car SET active = true WHERE id = :id", nativeQuery = true)
    void updateActiveToTrue(@Param("id") String id);

}
