package org.scaler.productmicroservice.controllers;

import org.junit.jupiter.api.Test;
import org.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;
import org.scaler.productmicroservice.repositories.ProductRepository;
import org.scaler.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void validgetProductByIdTest() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Laptop");
        product.setImage("Not available");
        product.setDescription("Hp Laptop");
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Laptop");
        product.setCategory(category);

        when(productService.getProductById(1L)).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product actualProduct = responseEntity.getBody();
        assertEquals(actualProduct, product);
        assertEquals(responseEntity, new ResponseEntity<>(product, HttpStatus.OK));
    }

    @Test
    void errotgetProductByIdTest() throws ProductNotFoundException {

        when(productService.getProductById(100L)).thenThrow(new ProductNotFoundException("No Product found with id 100"));
        assertThrows(ProductNotFoundException.class, ()-> productController.getProductById(100L));

    }

    @Test
    void validgetAllProductsTest(){
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        List<Product> products= new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        when(productRepository.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());

    }
}
