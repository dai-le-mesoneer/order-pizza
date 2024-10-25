package com.assignment.pizza.controller;

import com.assignment.pizza.common.Constants;
import com.assignment.pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.OrdersApi;
import org.openapitools.model.ListOrderRequest;
import org.openapitools.model.ResponseDTOIdDTO;
import org.openapitools.model.ResponseDTOListDTOOrderDTO;
import org.openapitools.model.UpdateOrderStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dai.le
 * @since 24/10/2024
 */

@RestController
@RequiredArgsConstructor
@Secured(value = {
        Constants.ROLE.CHEF,
        Constants.ROLE.DELIVERY,
        Constants.ROLE.RECEPTIONIST
})
public class OrderController implements OrdersApi {
    private final OrderService orderService;

    @Override
    public ResponseEntity<ResponseDTOListDTOOrderDTO> listOrders(ListOrderRequest r) {
        return ResponseEntity.ok(orderService.listOrders(r));
    }

    @Override
    public ResponseEntity<ResponseDTOIdDTO> updateOrderStatus(Long id, UpdateOrderStatusRequest updateOrderStatusRequest) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, updateOrderStatusRequest));
    }
}
