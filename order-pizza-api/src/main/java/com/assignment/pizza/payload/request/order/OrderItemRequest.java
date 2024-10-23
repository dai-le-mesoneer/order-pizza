package com.assignment.pizza.payload.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class OrderItemRequest {
    private Long id;
    private Integer quantity;
}
