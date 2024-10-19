package com.assignment.pizza.payload.request.product;

import com.assignment.pizza.payload.request.PageableRequest;
import lombok.*;

/**
 * @author dai-le-mesoneer
 * @since 10/17/24
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListProductRequest extends PageableRequest {
    private String type;
}
