package com.fast.miniproject.product.entity;

import com.fast.miniproject.auth.entity.User;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Long basketId;

    @ManyToOne(targetEntity=Product.class, fetch=FetchType.EAGER) // (1)
    @JoinColumn(name="products") // (2)
    private Product product;

    @ManyToOne(targetEntity= User.class, fetch=FetchType.EAGER) // (1)
    @JoinColumn(name="members") // (2)
    private User user;
}
