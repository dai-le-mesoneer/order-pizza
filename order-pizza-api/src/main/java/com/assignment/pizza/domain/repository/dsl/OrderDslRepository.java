package com.assignment.pizza.domain.repository.dsl;

import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.domain.entity.QOrderEntity;
import com.assignment.pizza.payload.request.order.ListOrderRequest;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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

        if (!Objects.isNull(request.getStatus())) {
            query.where(order.status.eq(request.getStatus()));
        }
        query.orderBy(order.createdDate.desc());

        return query.fetch();
    }
}
