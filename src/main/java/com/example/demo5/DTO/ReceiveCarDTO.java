package com.example.demo5.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveCarDTO {
    private String id;
    private String idCustomer;
    private String licensePlates;
    private String idAutomaker;
    private LocalDate dateReceive;
    private CustomerDTO customer;
    private Boolean isCreated;

}
