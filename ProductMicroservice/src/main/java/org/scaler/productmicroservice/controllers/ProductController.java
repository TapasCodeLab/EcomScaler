package org.scaler.productmicroservice.controllers;


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
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @RequestMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping("/category/{name}")
    public List<Product> getAllProductsInCategory(@PathVariable("name") String name){
        return productService.getAllProductsInCategory(name);
    }

    @PostMapping("/")
    public Product createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }


    @PatchMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProductById(id, product);

    }

    @PutMapping("/{id}")
    public Product replaceProductById(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProductById(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }



}
