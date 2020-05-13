package com.gear.test.service;

import com.gear.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public List save(List products) {
        return productRepository.save(products);
    }

    public List findAll() {
        return productRepository.findAll();
    }

}
