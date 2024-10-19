package com.assignment.pizza.service;

import com.assignment.pizza.payload.request.product.ListProductRequest;
import com.assignment.pizza.payload.response.ListDTO;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.payload.response.product.ProductDTO;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */
public interface ProductService {
    ResponseDTO<ListDTO<ProductDTO>> listProducts(ListProductRequest request);
}
