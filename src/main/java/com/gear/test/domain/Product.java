package com.gear.test.domain;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "product")
@Data
@NoArgsConstructor
@Entity
public class Product extends BaseEntity{

    @Column(nullable = false)
    private Long serial;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date date;

    public Product(Long serial, String name, String description, Date date) {
        this.serial = serial;
        this.name = name;
        this.description = description;
        this.date = date;
    }

}
