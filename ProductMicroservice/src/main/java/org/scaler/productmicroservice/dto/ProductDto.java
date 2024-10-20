package org.scaler.productmicroservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.scaler.productmicroservice.models.Category;

@Getter
@Setter
public class ProductDto {
        private String title;
        private String description;
        private Long price;
        private String image;
        private Category category;
}
