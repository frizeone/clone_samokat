package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.ProductsDTO;
import org.example.rssamocatclone.models.Products;

public class ProductsMapper {
    public static ProductsDTO toDTO(Products product) {
        ProductsDTO dto = new ProductsDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }

    public static Products toEntity(ProductsDTO dto) {
        Products product = new Products();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }
}
