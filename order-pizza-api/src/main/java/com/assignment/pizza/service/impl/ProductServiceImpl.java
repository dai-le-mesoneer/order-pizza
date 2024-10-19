package com.assignment.pizza.service.impl;

import com.assignment.pizza.domain.repository.ProductRepository;
import com.assignment.pizza.domain.repository.dsl.ProductDslRepository;
import com.assignment.pizza.mapper.ProductMapper;
import com.assignment.pizza.payload.request.product.ListProductRequest;
import com.assignment.pizza.payload.response.ListDTO;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.payload.response.product.ProductDTO;
import com.assignment.pizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDslRepository productDslRepository;
    private final ProductMapper productMapper;

    @Override
    public ResponseDTO<ListDTO<ProductDTO>> listProducts(ListProductRequest request) {
        var listProducts = productDslRepository.listAllProducts(request);
        return ResponseDTO.<ListDTO<ProductDTO>>newBuilder()
                .setSuccess(true)
                .setData(ListDTO.<ProductDTO>newBuilder()
                        .setItems(productMapper.mapAsList(listProducts))
                        .setTotalElement((long) listProducts.size())
                        .build())
                .build();
    }
}
