package com.example.demo5.Mapper;

import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.Entity.Customer;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer>{
}
