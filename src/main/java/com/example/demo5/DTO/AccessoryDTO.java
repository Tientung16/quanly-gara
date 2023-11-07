package com.example.demo5.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessoryDTO {
    private String id;
    private Integer cost;
    private String name;
    private Integer number;
    private String codeSupplier;
    private String guarantee;

}
