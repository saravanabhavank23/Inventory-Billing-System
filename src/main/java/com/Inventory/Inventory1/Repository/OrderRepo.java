package com.Inventory.Inventory1.Repository;

import com.Inventory.Inventory1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}