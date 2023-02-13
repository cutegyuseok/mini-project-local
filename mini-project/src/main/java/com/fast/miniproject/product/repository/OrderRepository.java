package com.fast.miniproject.product.repository;

import com.fast.miniproject.product.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {


}
