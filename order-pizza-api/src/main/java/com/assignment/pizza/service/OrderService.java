package com.assignment.pizza.service;

import com.assignment.pizza.payload.request.order.CreateOrderRequest;
import com.assignment.pizza.payload.request.order.ListOrderRequest;
import com.assignment.pizza.payload.request.order.UpdateOrderStatusRequest;
import com.assignment.pizza.payload.response.IdDTO;
import com.assignment.pizza.payload.response.ListDTO;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.payload.response.order.OrderDTO;

public interface OrderService {
    ResponseDTO<IdDTO> createOrder(CreateOrderRequest createOrderRequest);
    ResponseDTO<ListDTO<OrderDTO>> listOrders(ListOrderRequest request);
    ResponseDTO<IdDTO> updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);
}
