package com.assignment.pizza.payload.request;

import lombok.*;

/**
 * @author dai-le-mesoneer
 * @since 10/17/24
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableRequest {
    private Long page;
    private Long size;
    private String sort;
    private String direction;
    private Boolean ignorePage;
}