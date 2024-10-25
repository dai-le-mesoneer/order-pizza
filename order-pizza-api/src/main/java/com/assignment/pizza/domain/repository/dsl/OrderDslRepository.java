package com.assignment.pizza.domain.repository.dsl;

import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.domain.entity.QOrderEntity;
import com.assignment.pizza.domain.enums.OrderStatus;
import com.assignment.pizza.utils.PrincipalUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.ListOrderRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@Repository
@RequiredArgsConstructor
public class OrderDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QOrderEntity order = QOrderEntity.orderEntity;

    public List<OrderEntity> listOrders(ListOrderRequest request) {
        JPAQuery<OrderEntity> query = queryFactory.select(order)
                .from(order);

        var principal = PrincipalUtils.getPrincipal();

        if (Objects.nonNull(principal)) {
            if (principal.isReceptionist()) {
                query.where(order.status.eq(OrderStatus.PENDING.getValue()));
            }

            if (principal.isChef()) {
                query.where(order.status.eq(OrderStatus.CONFIRMED.getValue()));
            }

            if (principal.isDelivery()) {
                query.where(order.status.eq(OrderStatus.COMPLETED.getValue()));
            }
        }

        if (StringUtils.isNoneBlank(request.getSearchTerm())) {
            query.where(order.customerName.containsIgnoreCase(request.getSearchTerm()));
        }

        query.orderBy(order.createdDate.desc());

        return query.fetch();
    }
}
