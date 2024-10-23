package com.assignment.pizza.validator;

import com.assignment.pizza.payload.request.order.CreateOrderRequest;
import com.assignment.pizza.payload.response.ErrorDTO;

import java.util.List;

/**
 * @author dai.le
 * @since 21/10/2024
 */
public interface OrderValidator {
    List<ErrorDTO> validateCreateOrderRequest(CreateOrderRequest createOrderRequest);
}
