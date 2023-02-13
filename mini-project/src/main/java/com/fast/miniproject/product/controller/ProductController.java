package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.ProductIdList;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public ResponseDTO productDetail(@RequestParam Long products_id){
        return productService.selectProductDetail(products_id);
    }

    @PostMapping("/products/buy")
    public ResponseDTO buyProducts(@AuthenticationPrincipal LoginReqDTO user, ProductIdList products_id_list){

        return productService.buyProduct(products_id_list.getProducts_id_list(),user);
    }



}
