package com.fast.miniproject.product.repository.queryDsl;

import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoryCustom {

//    @Query(nativeQuery = true, value = "SELECT * FROM product as p WHERE p.product_id IN (:id_list)")
    List<Product> findAllByProductId(List<Integer> id_list);
}
