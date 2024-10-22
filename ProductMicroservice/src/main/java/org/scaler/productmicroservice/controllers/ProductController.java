package org.scaler.productmicroservice.controllers;


import org.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.scaler.productmicroservice.models.Product;
import org.scaler.productmicroservice.services.FakeStoreProductService;
import org.scaler.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    ProductController(ProductService productService){
        this.productService = productService;
    }


    @RequestMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @RequestMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping("/category/{name}")
    public ResponseEntity<List<Product>> getAllProductsInCategory(@PathVariable("name") String name){
        return productService.getAllProductsInCategory(name);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProductById(id, product);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProductById(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException{
        return productService.replaceProductById(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException{
        return productService.deleteProductById(id);
    }



}
