package com.example.demo5.Repository;

import com.example.demo5.Entity.Accessory;
import com.example.demo5.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select a from User a where a.email = :email and a.password = :password")
    Optional<User> check(@Param("email") String email,@Param("password") String password);
}
