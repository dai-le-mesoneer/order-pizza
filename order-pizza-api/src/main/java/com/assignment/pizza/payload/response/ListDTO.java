package com.assignment.pizza.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class ListDTO<T> {
    private List<T> items;
    private Long totalElement;
}
