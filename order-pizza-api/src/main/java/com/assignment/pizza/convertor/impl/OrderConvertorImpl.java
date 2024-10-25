package com.assignment.pizza.convertor.impl;

import com.assignment.pizza.convertor.OrderConvertor;
import com.assignment.pizza.domain.entity.OrderDetailsEntity;
import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.domain.entity.ProductEntity;
import com.assignment.pizza.domain.enums.OrderStatus;
import com.assignment.pizza.domain.repository.OrderRepository;
import com.assignment.pizza.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.OrderItemRequest;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dai.le
 * @since 21/10/2024
 */

@Component
@RequiredArgsConstructor
public class OrderConvertorImpl implements OrderConvertor {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderEntity convertToEntity(CreateOrderRequest request) {
        var now = new Timestamp(System.currentTimeMillis());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName(request.getCustomerName());
        orderEntity.setDeliverTo(request.getAddress());
        orderEntity.setPhoneNumber(request.getPhone());
        orderEntity.setStatus(OrderStatus.PENDING.getValue());
        orderEntity.setCreatedDate(now);
        orderEntity.setUpdatedDate(now);
        return orderEntity;
    }

    @Override
    public List<OrderDetailsEntity> convertToListOrderDetails(List<OrderItemRequest> requests, Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var productIds = requests.stream().map(OrderItemRequest::getId).toList();
        var productEntityMap = productRepository.findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(ProductEntity::getId, v -> v));

        return requests.stream()
                .filter(r -> productEntityMap.get(r.getId()) != null)
                .map(r -> {
                    OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
                    orderDetailsEntity.setOrder(order);
                    orderDetailsEntity.setProduct(productEntityMap.get(r.getId()));
                    orderDetailsEntity.setQuantity(r.getQuantity());
                    return orderDetailsEntity;
                }).toList();
    }
}
