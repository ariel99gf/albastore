package com.albastore.service;

import com.albastore.domain.Product;
import com.albastore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAllProducts() {
        Product product = new Product();
        product.name = "Test Product";
        product.price = 10.0;
        when(productRepository.listAll()).thenReturn(Collections.singletonList(product));

        List<Product> result = productService.listAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).name);
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.id = 1L;
        when(productRepository.findById(1L)).thenReturn(product);

        Product result = productService.findProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.name = "New Product";
        product.price = 20.0;

        productService.createProduct(product);

        verify(productRepository, times(1)).persist(product);
    }

    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product();
        existingProduct.id = 1L;
        existingProduct.name = "Existing Product";
        existingProduct.price = 30.0;

        Product updatedProduct = new Product();
        updatedProduct.name = "Updated Product";
        updatedProduct.price = 40.0;

        when(productRepository.findById(1L)).thenReturn(existingProduct);

        productService.updateProduct(1L, updatedProduct);

        assertEquals("Updated Product", existingProduct.name);
        assertEquals(40.0, existingProduct.price);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}