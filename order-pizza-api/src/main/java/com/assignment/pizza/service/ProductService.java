package com.assignment.pizza.service;


import org.openapitools.model.ListProductRequest;
import org.openapitools.model.ResponseDTOListDTOProductDTO;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */
public interface ProductService {
    ResponseDTOListDTOProductDTO listProducts(ListProductRequest request);
}
