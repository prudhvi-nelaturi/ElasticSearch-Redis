package com.catelog.demo.controller;

import com.catelog.demo.entity.Product;
import com.catelog.demo.entity.ProductDocument;
import com.catelog.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping("/search")
    public List<ProductDocument> search(@RequestParam String q) {
        return service.searchProducts(q);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

}
