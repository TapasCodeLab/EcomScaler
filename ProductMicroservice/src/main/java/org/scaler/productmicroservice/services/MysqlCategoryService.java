package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.repositories.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class MysqlCategoryService implements CategoryService{

    private CategoryRepository categoryRepository;

    MysqlCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
