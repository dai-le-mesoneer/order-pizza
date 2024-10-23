package com.assignment.pizza.payload.response.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class OrderDTO {
    private Long orderId;
    private String createdDate;
    private String name;
    private String phoneNumber;
    private Long price;
    private Integer status;
    private String address;
    private List<OrderItemDTO> items;
}
