package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.dto.FakeStoreProductDto;
import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        Category category = new Category();

        category.setTitle(dto.getCategory());

        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setCategory(category);
        product.setDescription(dto.getDescription());

        return product;
    }
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto dto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if(dto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto [] dtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        ArrayList<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto:dtos){
            Product product = convertFakeStoreDtoToProduct(dto);
            products.add(product);
        }
        return products;
    }
}
