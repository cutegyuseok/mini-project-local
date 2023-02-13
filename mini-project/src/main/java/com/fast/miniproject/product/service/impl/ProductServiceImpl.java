package com.fast.miniproject.product.service.impl;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.ProductDetailDTO;
import com.fast.miniproject.product.entity.Order;
import com.fast.miniproject.product.entity.OrderProductBridge;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.entity.PurchasedProduct;
import com.fast.miniproject.product.repository.OrderProductBridgeRepository;
import com.fast.miniproject.product.repository.OrderRepository;
import com.fast.miniproject.product.repository.ProductRepository;
import com.fast.miniproject.product.repository.PurchaseProductRepository;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final OrderRepository orderRepository;
    private final OrderProductBridgeRepository orderProductBridgeRepository;

    @Override
    public ResponseDTO selectProductDetail(Long product_id) {
        try {
            Product product = productRepository.getReferenceById(product_id);
            ProductDetailDTO productDetailDTO = new ProductDetailDTO(product);
            return new ResponseDTO<>(productDetailDTO);

        }catch (Exception e){
            return new ErrorResponseDTO(500,"해당 상품의 상세정보를 찾지 못했습니다.").toResponse();
        }
    }

    @Override
    @Transactional
    public ResponseDTO buyProduct(ArrayList<Integer> products_id_list, LoginReqDTO dto){
        User user = userRepository.findByEmail(dto.getEmail()).get();
        List<Product> productList = productRepository.findByProductId(products_id_list);
        if (user==null || productList.size()==0)return new ErrorResponseDTO(500,"구매에 실패하였습니다.").toResponse();
        if(!isAvailableToPurchase(user,productList))return new ErrorResponseDTO(500,"대출 가능 금액을 초과하였습니다.").toResponse();

        try {
            List<PurchasedProduct> purchasedProducts = purchaseProductRepository.saveAll(toSaveList(productList));
            Order order = orderRepository.save(new Order(user));
            for (PurchasedProduct product: purchasedProducts){
                orderProductBridgeRepository.save(new OrderProductBridge(product,order));
            }
            return new ResponseDTO(null);
        }catch (Exception e){
            return new ErrorResponseDTO(500,"구매에 실패하였습니다.").toResponse();
        }

    }

    static boolean isAvailableToPurchase(User user,List<Product> productList){
        long max = user.getSalary()*2;
        long sum =0;
        for (Product p :productList){
            sum+=p.getPrice();
        }
        if (max<sum) {
            return false;
        }else {
            return true;
        }
    }

    static List<PurchasedProduct> toSaveList(List<Product> productList){
        List<PurchasedProduct> list = new ArrayList<>();
        for (Product product : productList){
            list.add(new PurchasedProduct(product));
        }
        return list;
    }



}
