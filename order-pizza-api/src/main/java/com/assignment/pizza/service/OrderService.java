package com.assignment.pizza.service;

import org.openapitools.model.*;

public interface OrderService {
    ResponseDTOIdDTO createOrder(CreateOrderRequest createOrderRequest);
    ResponseDTOListDTOOrderDTO listOrders(ListOrderRequest request);
    ResponseDTOIdDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);
}
