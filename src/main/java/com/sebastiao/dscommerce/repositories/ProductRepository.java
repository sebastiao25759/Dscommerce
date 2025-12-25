package com.sebastiao.dscommerce.repositories;

import com.sebastiao.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
