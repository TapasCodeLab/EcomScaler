package org.scaler.productmicroservice.repositories;

import org.scaler.productmicroservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    void delete(Product product);

    Product save(Product product);

//    @Override
//    List<Product> findAll();
    @Override
    Page<Product> findAll(Pageable pageable);

    List<Product> findAlByCategoryTitle(String title);
}
