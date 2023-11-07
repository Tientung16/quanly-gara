package com.example.demo5.Repository;

import com.example.demo5.Entity.AutoMaker;
import com.example.demo5.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoMakerRepository extends JpaRepository<AutoMaker, String> {

    @Query("select a from AutoMaker a where a.name like %:name%")
    Page<AutoMaker> findAutoMakerByName(Pageable pageable, String name);
}
