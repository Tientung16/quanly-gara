package com.example.demo5.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private String id;
    private String idCustomer;
    private String licensePlates;
    private String codeAutomaker;
}
