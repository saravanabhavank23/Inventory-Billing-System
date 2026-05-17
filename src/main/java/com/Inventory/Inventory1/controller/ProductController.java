package com.Inventory.Inventory1.controller;

import com.Inventory.Inventory1.model.Product;
import com.Inventory.Inventory1.Productservice.Proservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private Proservice proservice;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return proservice.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return proservice.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return proservice.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        return proservice.updateProduct(id, updated);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return proservice.deleteProduct(id);
    }
    @GetMapping("/lowstock")
    public List<Product> getLowStockProducts() {
        return proservice.getLowStockProducts();
    }
}