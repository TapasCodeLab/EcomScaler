package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

}