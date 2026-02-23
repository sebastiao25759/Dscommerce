package com.sebastiao.dscommerce.repositories;

import com.sebastiao.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}