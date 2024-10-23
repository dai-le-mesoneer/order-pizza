package com.assignment.pizza.convertor;

import com.assignment.pizza.domain.entity.OrderDetailsEntity;
import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.payload.request.order.CreateOrderRequest;
import com.assignment.pizza.payload.request.order.OrderItemRequest;

import java.util.List;

/**
 * @author dai.le
 * @since 21/10/2024
 */
public interface OrderConvertor {
    OrderEntity convertToEntity(CreateOrderRequest request);
    List<OrderDetailsEntity> convertToListOrderDetails(List<OrderItemRequest> requests, Long orderId);
}
