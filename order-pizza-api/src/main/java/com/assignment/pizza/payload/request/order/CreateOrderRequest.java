package com.assignment.pizza.payload.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author dai-le-mesoneer
 * @since 21/10/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class CreateOrderRequest {
    private String customerName;
    private String phone;
    private String address;
    private List<OrderItemRequest> products;
}
