package com.fast.miniproject.product.repository;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.product.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> findAllByUser(User user);

}
