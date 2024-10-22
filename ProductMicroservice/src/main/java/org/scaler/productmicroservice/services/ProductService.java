package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;

    public ResponseEntity<List<Product>> getAllProducts();

    public ResponseEntity<List<Product>> getAllProductsInCategory(String category);

    public ResponseEntity<Product> createNewProduct(Product product);

    public ResponseEntity<Product> updateProductById(Long id, Product product) throws ProductNotFoundException;

    public ResponseEntity<Product> replaceProductById(Long id, Product product) throws ProductNotFoundException;

    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotFoundException;

}
