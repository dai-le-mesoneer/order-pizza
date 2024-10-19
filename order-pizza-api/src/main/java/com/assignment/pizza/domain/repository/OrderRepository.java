package com.assignment.pizza.domain.repository;

import com.assignment.pizza.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dai-le-mesoneer
 * @since 10/17/24
 */

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
