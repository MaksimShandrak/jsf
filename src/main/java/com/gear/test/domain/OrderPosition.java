package com.gear.test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@IdClass(OrderPositionPK.class)
public class OrderPosition {

    @Id
    private Long id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
