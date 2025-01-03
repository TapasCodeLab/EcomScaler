package org.scaler.productmicroservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private Long price;
    private String image;
    @ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn
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
