package com.albastore.service;

import com.albastore.domain.Product;
import com.albastore.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> listAllProducts() {
        return productRepository.listAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void createProduct(Product product) {
        productRepository.persist(product);
    }

    @Transactional
    public void updateProduct(Long id, Product product) {
        Product entity = productRepository.findById(id);
        if (entity != null) {
            entity.name = product.name;
            entity.price = product.price;
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}