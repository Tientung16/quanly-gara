package com.example.demo5.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
