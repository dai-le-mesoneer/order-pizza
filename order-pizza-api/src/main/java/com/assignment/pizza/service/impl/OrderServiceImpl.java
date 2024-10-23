package com.assignment.pizza.service.impl;

import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.convertor.OrderConvertor;
import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.domain.enums.OrderStatus;
import com.assignment.pizza.domain.repository.OrderDetailRepository;
import com.assignment.pizza.domain.repository.OrderRepository;
import com.assignment.pizza.domain.repository.dsl.OrderDslRepository;
import com.assignment.pizza.mapper.OrderMapper;
import com.assignment.pizza.payload.request.order.CreateOrderRequest;
import com.assignment.pizza.payload.request.order.ListOrderRequest;
import com.assignment.pizza.payload.request.order.UpdateOrderStatusRequest;
import com.assignment.pizza.payload.response.IdDTO;
import com.assignment.pizza.payload.response.ListDTO;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.payload.response.order.OrderDTO;
import com.assignment.pizza.service.OrderService;
import com.assignment.pizza.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dai-le-mesoneer
 * @since 21/10/2024
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDslRepository orderDslRepository;
    private final OrderMapper orderMapper;

    private final OrderConvertor orderConvertor;

    private final OrderValidator orderValidator;

    @Override
    @Transactional
    public ResponseDTO<IdDTO> createOrder(CreateOrderRequest createOrderRequest) {
        var listError = orderValidator.validateCreateOrderRequest(createOrderRequest);

        if (!CollectionUtils.isEmpty(listError)) {
            return ResponseDTO.<IdDTO>newBuilder()
                    .setSuccess(false)
                    .setCode(ErrorCode.BAD_REQUEST)
                    .setErrors(listError)
                    .build();
        }

        var order = orderConvertor.convertToEntity(createOrderRequest);
        var savedOrder = orderRepository.save(order);

        var orderDetails = orderConvertor.convertToListOrderDetails(createOrderRequest.getProducts(), savedOrder.getId());
        orderDetailRepository.saveAll(orderDetails);

        return ResponseDTO.<IdDTO>newBuilder()
                .setSuccess(true)
                .setData(IdDTO.newBuilder()
                        .setId(savedOrder.getId())
                        .build())
                .build();
    }

    @Override
    public ResponseDTO<ListDTO<OrderDTO>> listOrders(ListOrderRequest request) {
        var orders = orderDslRepository.listOrders(request);
        if (orders.isEmpty()) {
            return ResponseDTO.<ListDTO<OrderDTO>>newBuilder()
                    .setSuccess(true)
                    .setData(ListDTO.<OrderDTO>newBuilder()
                            .setItems(List.of())
                            .setTotalElement(0L)
                            .build())
                    .build();
        }
        var orderIds = orders.stream().map(OrderEntity::getId).toList();
        var orderDetails = orderDetailRepository.findAllByOrderIdIn(orderIds);

        var orderMap = orderDetails.stream().collect(Collectors.groupingBy(o -> o.getOrder().getId()));
        return ResponseDTO.<ListDTO<OrderDTO>>newBuilder()
                .setSuccess(true)
                .setData(ListDTO.<OrderDTO>newBuilder()
                        .setTotalElement((long) orders.size())
                        .setItems(orderMapper.mapAsList(orders, orderMap))
                        .build())
                .build();
    }

    @Override
    @Transactional
    public ResponseDTO<IdDTO> updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        if (!OrderStatus.isValid(request.getStatus())) {
            return ResponseDTO.<IdDTO>newBuilder()
                    .setSuccess(false)
                    .setCode(ErrorCode.BAD_REQUEST)
                    .setMessage("Status invalid!")
                    .build();
        }

        var order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalStateException("Object not found with id " + orderId));

        order.setStatus(request.getStatus());
        orderRepository.save(order);
        return ResponseDTO.<IdDTO>newBuilder()
                .setSuccess(true)
                .setData(IdDTO.newBuilder()
                        .setId(orderId)
                        .build())
                .build();
    }
}
