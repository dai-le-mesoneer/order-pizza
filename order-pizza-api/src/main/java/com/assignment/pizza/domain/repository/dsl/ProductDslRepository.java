package com.assignment.pizza.domain.repository.dsl;

import com.assignment.pizza.common.ProductType;
import com.assignment.pizza.domain.entity.ProductEntity;
import com.assignment.pizza.domain.entity.QProductEntity;
import com.assignment.pizza.payload.request.product.ListProductRequest;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

@Repository
@RequiredArgsConstructor
public class ProductDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QProductEntity product = QProductEntity.productEntity;

    public List<ProductEntity> listAllProducts(ListProductRequest request) {
        JPAQuery<ProductEntity> query = queryFactory.select(product).from(product);

        if (StringUtils.isNoneBlank(request.getType())) {
            query.where(product.type.eq(ProductType.parse(request.getType()).getId()));
        }

        return query.fetch();
    }
}
