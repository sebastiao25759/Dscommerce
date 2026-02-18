package com.sebastiao.dscommerce.services;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.sebastiao.dscommerce.dto.ProductDTO;
import com.sebastiao.dscommerce.dto.ProductMinDTO;
import com.sebastiao.dscommerce.entities.Product;
import com.sebastiao.dscommerce.repositories.ProductRepository;
import com.sebastiao.dscommerce.services.exceptions.DatabaseException;
import com.sebastiao.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(ProductMinDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product obj = new Product();
        copyDtoToEntity(dto, obj);
        obj = repository.save(obj);
        return new ProductDTO(obj);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product obj = repository.getReferenceById(id);
            copyDtoToEntity(dto, obj);
            obj = repository.save(obj);
            return new ProductDTO(obj);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product obj) {
        obj.setName(dto.getName());
        obj.setDescription(dto.getDescription());
        obj.setPrice(dto.getPrice());
        obj.setImgUrl(dto.getImgUrl());
    }
}