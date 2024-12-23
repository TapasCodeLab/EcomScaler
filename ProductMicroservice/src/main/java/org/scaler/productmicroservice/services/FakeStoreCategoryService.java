package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.dto.FakeStoreCategoryDto;
import org.scaler.productmicroservice.dto.FakeStoreProductDto;
import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService{

    private RestTemplate restTemplate;

    FakeStoreCategoryService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Category convertFakeStoreDtoToCategory(FakeStoreCategoryDto dto){
        Category category = new Category();
        category.setTitle(dto.getTitle());
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] dtos = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
        if(dtos == null){
            return null;
        }
        ArrayList<Category> categories = new ArrayList<>();
        for(String dto: dtos){
            Category c = new Category();
            c.setTitle(dto);
            categories.add(c);
        }
        return categories;
    }
}
