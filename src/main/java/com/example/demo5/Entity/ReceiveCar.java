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
@Table(name = "recieve_car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveCar implements Serializable, Persistable<String> {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "id_customer")
    private String idCustomer;

    @Column(name = "license_plates")
    private String licensePlates;

    @Column(name = "id_automaker")
    private String idAutomaker;

    @Column(name = "date_receive")
    private LocalDate dateReceive;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_created")
    private Boolean isCreated;

    @Override
    public boolean isNew() {
        return false;
    }
}
