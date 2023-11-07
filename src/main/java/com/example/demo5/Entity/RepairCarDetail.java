package com.example.demo5.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "repair_car_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairCarDetail implements Serializable, Persistable<String> {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "id_accessory")
    private String idAccessory;

    @Column(name = "number")
    private Integer number;

    @Column(name = "content")
    private String content;

    @Column(name = "id_repair")
    private String idRepair;

    @Column(name = "id_receive")
    private String idReceive;

    @Column(name = "id_customer")
    private String idCustomer;

    @Override
    public boolean isNew() {
        return false;
    }
}
