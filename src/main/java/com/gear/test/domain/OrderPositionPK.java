package com.gear.test.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderPositionPK implements Serializable {

    private Long id;

    private Order order;

}
