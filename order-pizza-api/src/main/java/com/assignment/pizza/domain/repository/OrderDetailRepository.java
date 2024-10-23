package com.assignment.pizza.domain.repository;

import com.assignment.pizza.domain.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dai-le-mesoneer
 * @since 10/17/24
 */

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailsEntity, Long> {
    @Query("SELECT o FROM OrderDetailsEntity o WHERE o.order.id IN (:orderIds)")
    List<OrderDetailsEntity> findAllByOrderIdIn(@Param("orderIds") List<Long> orderIds);
}
