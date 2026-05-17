package com.Inventory.Inventory1.Productservice;

import com.Inventory.Inventory1.Repository.OrderRepo;
import com.Inventory.Inventory1.Repository.productrepo;
import com.Inventory.Inventory1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private productrepo productRepository;

    public Order placeOrder(OrderRequest request) {

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrderRequest.OrderItemRequest itemReq : request.getItems()) {

            // Step 1 - Find the product
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemReq.getProductId()));

            // Step 2 - Check if enough stock is available
            if (product.getQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + product.getName()
                        + ". Available: " + product.getQuantity());
            }

            // Step 3 - Reduce the stock
            product.setQuantity(product.getQuantity() - itemReq.getQuantity());
            productRepository.save(product);

            // Step 4 - Create order item
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPrice(product.getPrice() * itemReq.getQuantity());
            orderItem.setOrder(order);

            orderItems.add(orderItem);

            // Step 5 - Add to total
            total += orderItem.getPrice();
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}