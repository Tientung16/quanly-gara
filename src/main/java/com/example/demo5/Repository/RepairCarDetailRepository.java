package com.example.demo5.Repository;

import com.example.demo5.Entity.RepairCar;
import com.example.demo5.Entity.RepairCarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepairCarDetailRepository extends JpaRepository<RepairCarDetail, String> {
    List<RepairCarDetail> findAllByIdRepair(String id);

    @Query("SELECT a FROM RepairCarDetail a WHERE a.idReceive = :idReceive")
    List<RepairCarDetail> findRepairCarDetailsByRepairId(@Param("idReceive") String idReceive);

    @Query("SELECT a FROM RepairCarDetail a WHERE a.idCustomer = :idCustomer")
    List<RepairCarDetail> findRepairCarDetailsByIdCutomer(@Param("idCustomer") String idCustomer);
}
