package org.scaler.productmicroservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.scaler.productmicroservice.models.Category;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Long price;
    private String category;
    private String description;
    private String image;
}
