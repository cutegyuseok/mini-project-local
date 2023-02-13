package com.fast.miniproject.product.service;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    ResponseDTO selectProductDetail(Long product_id);

    ResponseDTO buyProduct(ArrayList<Integer> products_id_list, LoginReqDTO user);
}
