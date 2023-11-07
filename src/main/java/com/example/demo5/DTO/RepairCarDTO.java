package com.example.demo5.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairCarDTO {
    private String id;
    private String idReceive;
    private LocalDate dateRepair;
    private Integer wage;
    private List<RepairCarDetailDTO> repairCarDetail;
    private CustomerDTO customer;
    private Integer totalMoney	;
    private Integer active;
}
