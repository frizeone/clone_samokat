package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.CategoriesDTO;
import org.example.rssamocatclone.models.Categories;

public class CategoriesMapper {
    public static CategoriesDTO toDTO(Categories category) {
        CategoriesDTO dto = new CategoriesDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public static Categories toEntity(CategoriesDTO dto) {
        Categories category = new Categories();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}
