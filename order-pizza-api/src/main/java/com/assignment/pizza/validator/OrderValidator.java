package com.assignment.pizza.validator;

import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.ErrorDTO;

import java.util.List;

/**
 * @author dai.le
 * @since 21/10/2024
 */
public interface OrderValidator {
    List<ErrorDTO> validateCreateOrderRequest(CreateOrderRequest createOrderRequest);
}
