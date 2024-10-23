package com.assignment.pizza.controller;

import com.assignment.pizza.payload.request.order.CreateOrderRequest;
import com.assignment.pizza.payload.request.order.ListOrderRequest;
import com.assignment.pizza.payload.request.order.UpdateOrderStatusRequest;
import com.assignment.pizza.payload.response.IdDTO;
import com.assignment.pizza.payload.response.ListDTO;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.payload.response.order.OrderDTO;
import com.assignment.pizza.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author dai.le
 * @since 21/10/2024
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@Tag(name = "Order APIs")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(description = "Create order", summary = "Create order API. Public API")
    public ResponseDTO<IdDTO> createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }


    @GetMapping
    @Operation(description = "List order", summary = "List order API")
    @Secured(value = {
            "CHEF", "RECEPTIONIST", "DELIVERY"
    })
    public ResponseDTO<ListDTO<OrderDTO>> listOrders(@ModelAttribute ListOrderRequest request) {
        return orderService.listOrders(request);
    }

    @PutMapping("{id}")
    @Operation(description = "Update order status", summary = "Update order status")
    public ResponseDTO<IdDTO> updateOrderStatus(
            @PathVariable("id") Long orderId,
            @RequestBody UpdateOrderStatusRequest request) {
        return orderService.updateOrderStatus(orderId, request);
    }
}
