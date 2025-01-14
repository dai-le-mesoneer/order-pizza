package com.assignment.pizza.domain.repository;

import com.assignment.pizza.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dai-le-mesoneer
 * @since 10/17/24
 */

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
