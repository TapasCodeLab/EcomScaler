package org.scaler.productmicroservice.repositories;

import org.scaler.productmicroservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);

    @Override
    List<Category> findAll();
}
