package com.sebastiao.dscommerce.services;

import com.sebastiao.dscommerce.dto.OrderDTO;
import com.sebastiao.dscommerce.entities.Order;
import com.sebastiao.dscommerce.repositories.OrderRepository;
import com.sebastiao.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

}