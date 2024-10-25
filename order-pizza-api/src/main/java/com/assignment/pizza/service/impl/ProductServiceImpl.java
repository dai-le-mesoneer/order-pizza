package com.assignment.pizza.service.impl;

import com.assignment.pizza.domain.repository.dsl.ProductDslRepository;
import com.assignment.pizza.mapper.ProductMapper;
import com.assignment.pizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ListDTOProductDTO;
import org.openapitools.model.ListProductRequest;
import org.openapitools.model.ResponseDTOListDTOProductDTO;
import org.springframework.stereotype.Service;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDslRepository productDslRepository;
    private final ProductMapper productMapper;

    @Override
    public ResponseDTOListDTOProductDTO listProducts(ListProductRequest request) {
        var listProducts = productDslRepository.listAllProducts(request);

        ResponseDTOListDTOProductDTO response = new ResponseDTOListDTOProductDTO();
        response.setSuccess(true);
        ListDTOProductDTO listProductDTO = new ListDTOProductDTO();
        listProductDTO.setItems(productMapper.mapAsList(listProducts));
        listProductDTO.setTotalElement((long) listProducts.size());
        response.setData(listProductDTO);
        return response;
    }
}
