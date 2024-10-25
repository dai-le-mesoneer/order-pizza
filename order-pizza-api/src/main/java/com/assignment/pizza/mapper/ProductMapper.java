package com.assignment.pizza.mapper;

import com.assignment.pizza.common.ProductType;
import com.assignment.pizza.domain.entity.ProductEntity;
import org.openapitools.model.ProductDTO;
import org.springframework.stereotype.Component;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Component
public class ProductMapper extends BaseMapper<ProductEntity, ProductDTO> {
    @Override
    public ProductDTO map(ProductEntity input) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(input.getName());
        productDTO.setId(input.getId());
        productDTO.setImage(input.getImage());
        productDTO.setPrice(input.getPrice());
        productDTO.setType(ProductType.of(input.getType()).getName());
        return productDTO;
    }
}
