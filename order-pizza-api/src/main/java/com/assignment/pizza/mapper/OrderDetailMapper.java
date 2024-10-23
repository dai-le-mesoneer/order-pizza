package com.assignment.pizza.mapper;

import com.assignment.pizza.domain.entity.OrderDetailsEntity;
import com.assignment.pizza.payload.response.order.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@Component
public class OrderDetailMapper extends BaseMapper<OrderDetailsEntity, OrderItemDTO> {
    @Override
    public OrderItemDTO map(OrderDetailsEntity input) {
        return OrderItemDTO.newBuilder()
                .setProductId(input.getProduct().getId())
                .setProductName(input.getProduct().getName())
                .setQuantity(input.getQuantity())
                .build();
    }
}
