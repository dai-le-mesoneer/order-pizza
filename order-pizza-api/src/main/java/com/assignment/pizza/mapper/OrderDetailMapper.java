package com.assignment.pizza.mapper;

import com.assignment.pizza.domain.entity.OrderDetailsEntity;
import org.openapitools.model.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@Component
public class OrderDetailMapper extends BaseMapper<OrderDetailsEntity, OrderItemDTO> {
    @Override
    public OrderItemDTO map(OrderDetailsEntity input) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductId(input.getProduct().getId());
        itemDTO.setProductName(input.getProduct().getName());
        itemDTO.setQuantity(input.getQuantity());
        return itemDTO;
    }
}
