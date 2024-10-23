package com.assignment.pizza.controller;

import com.assignment.pizza.payload.request.product.ListProductRequest;
import com.assignment.pizza.payload.response.ListDTO;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.payload.response.product.ProductDTO;
import com.assignment.pizza.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
@Tag(name = "Product APIs")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(description = "List all product. Public API", summary = "List all product. Public API")
    public ResponseDTO<ListDTO<ProductDTO>> getAllProduct(@ModelAttribute ListProductRequest request) {
        return productService.listProducts(request);
    }
}
