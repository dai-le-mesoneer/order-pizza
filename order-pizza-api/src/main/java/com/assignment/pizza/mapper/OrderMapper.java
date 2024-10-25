package com.assignment.pizza.mapper;

import com.assignment.pizza.domain.entity.OrderDetailsEntity;
import com.assignment.pizza.domain.entity.OrderEntity;
import com.assignment.pizza.utils.DatetimeUtils;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@Component
@RequiredArgsConstructor
public class OrderMapper extends BaseMapper<OrderEntity, OrderDTO> {
    private final OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDTO map(OrderEntity input) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(input.getId());
        orderDTO.setAddress(input.getDeliverTo());
        orderDTO.setCreatedDate(DatetimeUtils.timestampToStringWithDefaultZone(input.getCreatedDate()));
        orderDTO.setPhoneNumber(input.getPhoneNumber());
        orderDTO.setName(input.getCustomerName());
        orderDTO.setStatus(input.getStatus());
        return orderDTO;
    }

    public List<OrderDTO> mapAsList(List<OrderEntity> orderEntities, Map<Long, List<OrderDetailsEntity>> orderDetailMap) {
        var listOrder = mapAsList(orderEntities);

        return listOrder.stream().peek(o -> {
            o.setItems(orderDetailMapper.mapAsList(orderDetailMap.get(o.getOrderId())));
            o.setPrice(calcPrice(orderDetailMap.get(o.getOrderId())));
        }).toList();
    }

    private long calcPrice(List<OrderDetailsEntity> orderDetailsEntities) {
        return orderDetailsEntities
                .stream()
                .mapToLong(orderDetail -> orderDetail.getProduct().getPrice() * orderDetail.getQuantity())
                .sum();
    }
}
