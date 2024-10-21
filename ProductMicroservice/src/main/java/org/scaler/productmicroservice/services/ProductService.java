package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public List<Product> getAllProductsInCategory(String category);

    public Product createNewProduct(Product product);

    public Product updateProductById(Long id, Product product);

    public Product replaceProductById(Long id, Product product);

    public Product deleteProductById(Long id);

}
