package com.example.demo5.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairCarDetailDTO {
    private String id;
    private String idAccessory;
    private Integer number;
    private String content;
    private String idRepair;
    private String idReceive;
    private String idCustomer;


}
