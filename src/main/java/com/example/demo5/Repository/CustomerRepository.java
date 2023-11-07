package com.example.demo5.Repository;

import com.example.demo5.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
//    Page<Customer> findBy(Pageable pageable);
    @Query("select a from Customer a where a.name like %:name%")
    Page<Customer> findCustomerByName(Pageable pageable,String name);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
