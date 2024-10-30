package org.scaler.productmicroservice.services;

import org.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import org.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.scaler.productmicroservice.models.Category;
import org.scaler.productmicroservice.models.Product;
import org.scaler.productmicroservice.repositories.CategoryRepository;
import org.scaler.productmicroservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class MysqlProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    MysqlProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("No Product found with id "+id);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProductsInCategory(String category) {
        List<Product> products = productRepository.findAlByCategoryTitle(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> createNewProduct(Product product) throws CategoryNotFoundException{
        Category category = product.getCategory();
        if(category.getId() == null){
            Category savedcategory = categoryRepository.save(category);
            product.setCategory(savedcategory);
        } else{
            Optional<Category> savedcategory = categoryRepository.findById(category.getId());
            if(savedcategory.isEmpty()){
                throw new CategoryNotFoundException("No Category details found with with Category id :"+category.getId());
            } else {
                product.setCategory(savedcategory.get());
            }
        }
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProductById(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> savedProduct = productRepository.findById(id);
        if(savedProduct.isEmpty()){
            throw new ProductNotFoundException("No Product found with Product id: "+id);
        } else{
            Product newProduct = savedProduct.get();
            if(product.getTitle()!=null){newProduct.setTitle(product.getTitle());}
            if(product.getDescription()!=null){newProduct.setDescription(product.getDescription());}
            if(product.getPrice()!=null){newProduct.setPrice(product.getPrice());}
            if(product.getImage()!=null){newProduct.setImage(product.getImage());}
            if(product.getCategory()!=null){
                Category category = product.getCategory();
                if(category.getId() == null){
                    Category savedcategory = categoryRepository.save(category);
                    newProduct.setCategory(savedcategory);
                } else{
                    Optional<Category> savedCategory = categoryRepository.findById(category.getId());
                    if(savedCategory.isEmpty()){
                        throw new CategoryNotFoundException("No Category details found with with Category id :"+category.getId());
                    } else {
                        newProduct.setCategory(savedCategory.get());
                    }
                }
            }
            productRepository.save(newProduct);
            return new ResponseEntity<>(newProduct,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Product> replaceProductById(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> savedProduct = productRepository.findById(id);
        if(savedProduct.isEmpty()){
            throw new ProductNotFoundException("No Product found with Product id: "+id);
        } else{
            Product newProduct = savedProduct.get();
            newProduct.setTitle(product.getTitle());
            newProduct.setDescription(product.getDescription());
            newProduct.setPrice(product.getPrice());
            newProduct.setImage(product.getImage());
            if(product.getCategory()!=null){
                Category category = product.getCategory();
                if(category.getId() == null){
                    Category savedcategory = categoryRepository.save(category);
                    newProduct.setCategory(savedcategory);
                } else{
                    Optional<Category> savedCategory = categoryRepository.findById(category.getId());
                    if(savedCategory.isEmpty()){
                        throw new CategoryNotFoundException("No Category details found with with Category id :"+category.getId());
                    } else {
                        newProduct.setCategory(savedCategory.get());
                    }
                }
            } else{
                newProduct.setCategory(null);
            }
            productRepository.save(newProduct);
            return new ResponseEntity<>(newProduct,HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> savedProduct = productRepository.findById(id);
        if(savedProduct.isEmpty()){
            throw new ProductNotFoundException("No Product found with Product id: "+id);
        } else{
            productRepository.delete(savedProduct.get());
            return new ResponseEntity<>(savedProduct.get(), HttpStatus.OK);
        }
    }
}
