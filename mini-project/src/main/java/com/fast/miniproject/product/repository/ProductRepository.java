package com.fast.miniproject.product.repository;

import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM product as p WHERE p.product_id IN (:id_list)")
    List<Product> findByProductId(@Param("id_list") List<Integer> id_list);
}
