package org.scaler.productmicroservice.controllers;

import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping("/")
    public List<Category> getcategoryList(){
        return categoryService.getAllCategories();
    }
}
