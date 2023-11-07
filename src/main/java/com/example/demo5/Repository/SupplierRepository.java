package com.example.demo5.Repository;

import com.example.demo5.Entity.Customer;
import com.example.demo5.Entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

    @Query("select a from Supplier a where a.name like %:name%")
    Page<Supplier> findSupplierByName(Pageable pageable, String name);
}
