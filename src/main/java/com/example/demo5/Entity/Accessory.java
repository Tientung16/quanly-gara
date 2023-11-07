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
@Table(name = "accessory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accessory implements Serializable, Persistable<String> {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private Integer number;

    @Column(name = "code_supplier")
    private String codeSupplier;

    @Column(name = "guarantee")
    private String guarantee;

    @Override
    public boolean isNew() {
        return false;
    }

}
