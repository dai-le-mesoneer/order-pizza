package com.assignment.pizza.validator.impl;

import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.utils.CommonUtils;
import com.assignment.pizza.utils.ErrorUtils;
import com.assignment.pizza.validator.OrderValidator;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.ErrorDTO;
import org.openapitools.model.OrderItemRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dai.le
 * @since 21/10/2024
 */

@Component
public class OrderValidatorImpl implements OrderValidator {
    @Override
    public List<ErrorDTO> validateCreateOrderRequest(CreateOrderRequest createOrderRequest) {
        return ErrorUtils.createListErrors(
                validateCustomerName(createOrderRequest.getCustomerName()),
                validatePhoneNumber(createOrderRequest.getPhone()),
                validateAddress(createOrderRequest.getAddress()),
                validateListProduct(createOrderRequest.getProducts())
        );
    }

    private ErrorDTO validateListProduct(List<OrderItemRequest> products) {
        return products.isEmpty() ? ErrorUtils.createErrorDTO("products", ErrorCode.NOT_EMPTY) : null;
    }

    private ErrorDTO validateAddress(String address) {
        return StringUtils.isBlank(address) ? ErrorUtils.createErrorDTO("address", ErrorCode.REQUIRED) : null;
    }

    private ErrorDTO validatePhoneNumber(String phone) {
        var error = StringUtils.isBlank(phone) ? ErrorUtils.createErrorDTO("phone", ErrorCode.REQUIRED) : null;
        if (error == null) {
            error = CommonUtils.isInvalidPhone(phone) ? ErrorUtils.createErrorDTO("phone", ErrorCode.INVALID) : null;
        }
        return error;
    }

    private ErrorDTO validateCustomerName(String customerName) {
        return StringUtils.isBlank(customerName) ? ErrorUtils.createErrorDTO("name", ErrorCode.REQUIRED) : null;
    }
}
