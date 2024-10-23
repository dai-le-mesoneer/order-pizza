package com.assignment.pizza.payload.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOrderRequest {
    private Integer status;
}
