package com.fast.miniproject.product.repository.queryDsl;

import com.fast.miniproject.product.entity.Product;
import com.querydsl.core.QueryResults;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import static com.fast.miniproject.product.entity.QProduct.product;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom{
    public ProductRepositoryImpl() {
       super(Product.class);
    }

    @Override
    public List<Product> findAllByProductId(List<Integer> id_list) {
        Long[] arr = new Long[id_list.size()];
        for (int i=0;i<id_list.size();i++){
            arr[i] = Long.valueOf(id_list.get(i));
        }
         QueryResults<Product> results =from(product).where(product.productId.in(arr)).fetchResults();
         return results.getResults();
    }
}
