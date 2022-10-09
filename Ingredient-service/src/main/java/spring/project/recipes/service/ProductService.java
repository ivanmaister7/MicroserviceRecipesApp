package src.main.java.spring.project.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.main.java.spring.project.recipes.domain.Product;
import src.main.java.spring.project.recipes.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(Long productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Long> findProductsByNames(List<String> names) {
        return productRepository.findProductByNames(names);
    }

    public List<Product> findAll() {
        return productRepository.findAllProducts();
    }
}
