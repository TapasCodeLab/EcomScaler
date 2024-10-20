package org.scaler.productmicroservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private Long id;
    private String title;
    private String category_desc;

    public Category(){}
}
