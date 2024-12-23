package org.scaler.productmicroservice.controllers;


import org.scaler.productmicroservice.commons.AuthCommons;
import org.scaler.productmicroservice.dto.UserDto;
import org.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import org.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.scaler.productmicroservice.models.Product;
import org.scaler.productmicroservice.services.FakeStoreProductService;
import org.scaler.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthCommons authCommons;

    @Autowired
    ProductController(ProductService productService, AuthCommons authCommons){
        this.productService = productService;
        this.authCommons = authCommons;
    }


    @RequestMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        UserDto userDto = authCommons.validateToken(token);
//        if(userDto==null){
//            //Raise exception
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }else{
//            return productService.getProductById(id);
//        }
        return productService.getProductById(id);
    }

    @RequestMapping("/")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("sortBy") String sortBy){
        return productService.getAllProducts(pageNumber, pageSize, sortBy);
    }
    //localhost:8080/products/?pageNumber=0&pageSize=5&sortBy=id

    @RequestMapping("/category/{name}")
    public ResponseEntity<List<Product>> getAllProductsInCategory(@PathVariable("name") String name){
        return productService.getAllProductsInCategory(name);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) throws CategoryNotFoundException {
        return productService.createNewProduct(product);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException, CategoryNotFoundException{
        return productService.updateProductById(id, product);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProductById(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException, CategoryNotFoundException{
        return productService.replaceProductById(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException{
        return productService.deleteProductById(id);
    }



}
