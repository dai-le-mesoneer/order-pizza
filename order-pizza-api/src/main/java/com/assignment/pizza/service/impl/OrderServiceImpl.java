package com.assignment.pizza.service.impl;

import com.assignment.pizza.convertor.OrderConvertor;
import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.domain.enums.OrderStatus;
import com.assignment.pizza.domain.repository.OrderDetailRepository;
import com.assignment.pizza.domain.repository.OrderRepository;
import com.assignment.pizza.domain.repository.dsl.OrderDslRepository;
import com.assignment.pizza.mapper.OrderMapper;
import com.assignment.pizza.service.OrderService;
import com.assignment.pizza.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.*;
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
    public ResponseDTOIdDTO createOrder(CreateOrderRequest createOrderRequest) {
        var listError = orderValidator.validateCreateOrderRequest(createOrderRequest);

        if (!CollectionUtils.isEmpty(listError)) {
            ResponseDTOIdDTO res = new ResponseDTOIdDTO();
            res.setSuccess(false);
            res.setCode(ResponseDTOIdDTO.CodeEnum.BAD_REQUEST);
            res.setErrors(listError);
            return res;
        }

        var order = orderConvertor.convertToEntity(createOrderRequest);
        var savedOrder = orderRepository.save(order);

        var orderDetails = orderConvertor.convertToListOrderDetails(createOrderRequest.getProducts(), savedOrder.getId());
        orderDetailRepository.saveAll(orderDetails);

        ResponseDTOIdDTO res = new ResponseDTOIdDTO();
        res.setSuccess(true);
        IdDTO idDTO = new IdDTO();
        idDTO.setId(savedOrder.getId());
        res.setData(idDTO);
        return res;
    }

    @Override
    public ResponseDTOListDTOOrderDTO listOrders(ListOrderRequest request) {
        var orders = orderDslRepository.listOrders(request);
        if (orders.isEmpty()) {
            ResponseDTOListDTOOrderDTO res = new ResponseDTOListDTOOrderDTO();
            res.setSuccess(true);

            ListDTOOrderDTO list = new ListDTOOrderDTO();
            list.setItems(List.of());
            list.setTotalElement(0L);
            res.setData(list);
            return res;
        }
        var orderIds = orders.stream().map(OrderEntity::getId).toList();
        var orderDetails = orderDetailRepository.findAllByOrderIdIn(orderIds);

        var orderMap = orderDetails.stream().collect(Collectors.groupingBy(o -> o.getOrder().getId()));

        ResponseDTOListDTOOrderDTO res = new ResponseDTOListDTOOrderDTO();
        res.setSuccess(true);

        ListDTOOrderDTO list = new ListDTOOrderDTO();
        list.setTotalElement((long) orders.size());
        list.setItems(orderMapper.mapAsList(orders, orderMap));
        res.setData(list);

        return res;
    }

    @Override
    @Transactional
    public ResponseDTOIdDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        if (!OrderStatus.isValid(request.getStatus())) {
            ResponseDTOIdDTO res = new ResponseDTOIdDTO();
            res.setSuccess(false);
            res.setCode(ResponseDTOIdDTO.CodeEnum.BAD_REQUEST);
            res.setMessage("Status invalid!");
            return res;
        }

        var order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalStateException("Object not found with id " + orderId));

        order.setStatus(request.getStatus());
        orderRepository.save(order);

        ResponseDTOIdDTO res = new ResponseDTOIdDTO();
        res.setSuccess(true);
        IdDTO idDTO = new IdDTO();
        idDTO.setId(orderId);
        res.setData(idDTO);
        return res;
    }
}
