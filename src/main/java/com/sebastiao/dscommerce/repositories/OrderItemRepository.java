package com.sebastiao.dscommerce.repositories;

import com.sebastiao.dscommerce.entities.OrderItem;
import com.sebastiao.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}