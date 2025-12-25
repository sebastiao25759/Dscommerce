package com.sebastiao.dscommerce.controllers;

import com.sebastiao.dscommerce.entities.Product;
import com.sebastiao.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String findAll() {
        Optional<Product> result = repository.findById(1L);
        Product product = result.get();
        return product.getName();
    }
}
