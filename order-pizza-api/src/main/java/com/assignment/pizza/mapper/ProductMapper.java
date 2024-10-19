package com.assignment.pizza.mapper;

import com.assignment.pizza.common.ProductType;
import com.assignment.pizza.domain.entity.ProductEntity;
import com.assignment.pizza.payload.response.product.ProductDTO;
import org.springframework.stereotype.Component;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Component
public class ProductMapper extends BaseMapper<ProductEntity, ProductDTO> {
    @Override
    public ProductDTO map(ProductEntity input) {
        return ProductDTO.newBuilder()
                .setId(input.getId())
                .setImage(input.getImage())
                .setName(input.getName())
                .setPrice(input.getPrice())
                .setType(ProductType.of(input.getType()).getName())
                .build();
    }
}
