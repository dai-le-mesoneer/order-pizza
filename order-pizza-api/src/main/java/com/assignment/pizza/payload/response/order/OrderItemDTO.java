package com.assignment.pizza.payload.response.order;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
}
