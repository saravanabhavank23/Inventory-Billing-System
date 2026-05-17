package com.Inventory.Inventory1.Repository;

import com.Inventory.Inventory1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface productrepo extends JpaRepository<Product, Long> {
    List<Product> findByQuantityLessThan(int quantity);
}