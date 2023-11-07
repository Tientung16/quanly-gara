package com.example.demo5.Repository;

import com.example.demo5.Entity.Accessory;
import com.example.demo5.Entity.AutoMaker;
import com.example.demo5.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface AccessoryRepository extends JpaRepository<Accessory, String> {

    @Query("select a from Accessory a where a.name like %:name%")
    Page<Accessory> findAccessoryByName(Pageable pageable, String name);
}
