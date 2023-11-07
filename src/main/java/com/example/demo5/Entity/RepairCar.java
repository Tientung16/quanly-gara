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
import java.time.LocalDate;

@Entity
@Table(name = "repair_car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairCar implements Serializable, Persistable<String> {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "id_receive")
    private String idReceive;

    @Column(name = "date_repair")
    private LocalDate dateRepair;

    @Column(name = "wage")
    private Integer wage;

    @Column(name = "total_money")
    private Integer totalMoney	;


    @Column(name = "id_customer")
    private String idCustomer;

    @Override
    public boolean isNew() {
        return false;
    }
}
