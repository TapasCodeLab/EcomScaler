package org.scaler.productmicroservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String title;
    private String description;
    private Long price;
    private String image;
    private Category category;

    public Product(){}

    public Product(String title, String description, Long price, String image, Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }


}
