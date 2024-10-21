package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.dto.FakeStoreProductDto;
import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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

    private FakeStoreProductDto convertProductToFakeStoreDto(Product p){
        FakeStoreProductDto dto =  new FakeStoreProductDto();
        if(p.getTitle() != null){dto.setTitle(p.getTitle());}
        if(p.getDescription() != null){dto.setDescription(p.getDescription());}
        if(p.getPrice() != null){dto.setPrice(p.getPrice());}
        if(p.getImage() != null){dto.setImage(p.getImage());}
        if(p.getCategory() != null){dto.setCategory(p.getCategory().getTitle());}
        return dto;
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
        if(dtos == null){
            return null;
        }
        ArrayList<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto:dtos){
            Product product = convertFakeStoreDtoToProduct(dto);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsInCategory(String category) {
        FakeStoreProductDto [] dtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+category, FakeStoreProductDto[].class);
        if(dtos == null){
            return null;
        }
        ArrayList<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto:dtos){
            Product product = convertFakeStoreDtoToProduct(dto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createNewProduct(Product product) {
        FakeStoreProductDto dto = restTemplate.postForObject("https://fakestoreapi.com/products", convertProductToFakeStoreDto(product), FakeStoreProductDto.class);
        if(dto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        FakeStoreProductDto inputDto = convertProductToFakeStoreDto(product);
        inputDto.setId(id);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(inputDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto dto = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PATCH, requestCallback, responseExtractor);
        if(dto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public Product replaceProductById(Long id, Product product) {
        FakeStoreProductDto inputDto = convertProductToFakeStoreDto(product);
        inputDto.setId(id);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(inputDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto dto = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        if(dto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public Product deleteProductById(Long id) {
        FakeStoreProductDto inputDto = new FakeStoreProductDto();
        inputDto.setId(id);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(inputDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto dto = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, requestCallback, responseExtractor);
        if(dto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(dto);
    }
}
