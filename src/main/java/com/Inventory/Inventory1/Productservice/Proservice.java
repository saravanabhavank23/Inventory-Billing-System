package com.Inventory.Inventory1.Productservice;
import com.Inventory.Inventory1.model.Product;
import com.Inventory.Inventory1.Repository.productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class Proservice {
    @Autowired
    private productrepo productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, Product updated) {
        Product product = getProductById(id);
        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setQuantity(updated.getQuantity());
        product.setCategory(updated.getCategory());
        return productRepository.save(product);
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
    public List<Product> getLowStockProducts() {
        return productRepository.findByQuantityLessThan(10);
    }
}
