package com.catelog.demo.service;

import com.catelog.demo.entity.Product;
import com.catelog.demo.entity.ProductDocument;
import com.catelog.demo.repository.ProductRepository;
import com.catelog.demo.repository.ProductSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSearchRepository searchRepository;


    public Product saveProduct(Product product) {
        Product saved = productRepository.save(product);
        searchRepository.save(new ProductDocument(saved.getId(), saved.getName(), saved.getDescription()));
        return saved;
    }

    public List<ProductDocument> searchProducts(String keyword) {
        return searchRepository.findByNameContaining(keyword);
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        simulateSlowService();
        return productRepository.findById(id).orElse(null);
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}

