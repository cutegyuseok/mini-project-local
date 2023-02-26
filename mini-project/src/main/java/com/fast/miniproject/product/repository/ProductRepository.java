package com.fast.miniproject.product.repository;

import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.repository.queryDsl.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    Page<Product> findByPriceLessThanEqual(int price, Pageable pageable);

    Optional<Product> findByProductId(Long id);

    Page<Product> findAll(Pageable pageable);
}