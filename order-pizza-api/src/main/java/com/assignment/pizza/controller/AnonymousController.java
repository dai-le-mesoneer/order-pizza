package com.assignment.pizza.controller;

import com.assignment.pizza.service.OrderService;
import com.assignment.pizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PublicApi;
import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.ListProductRequest;
import org.openapitools.model.ResponseDTOIdDTO;
import org.openapitools.model.ResponseDTOListDTOProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dai.le
 * @since 24/10/2024
 */

@RestController
@RequiredArgsConstructor
public class AnonymousController implements PublicApi {
    private final ProductService productService;
    private final OrderService orderService;
    @Override
    public ResponseEntity<ResponseDTOIdDTO> createOrder(CreateOrderRequest createOrderRequest) {
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest));
    }

    @Override
    public ResponseEntity<ResponseDTOListDTOProductDTO> getAllProduct(ListProductRequest r) {
        return ResponseEntity.ok(productService.listProducts(r));
    }
}
