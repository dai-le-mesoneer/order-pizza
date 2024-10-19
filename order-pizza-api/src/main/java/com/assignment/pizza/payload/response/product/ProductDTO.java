package com.assignment.pizza.payload.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class ProductDTO {
    private Long id;
    private String name;
    private String type;
    private Long price;
    private String image;
}
