package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.CategoriesDTO;

import java.util.List;

public interface CategoriesService {

    List<CategoriesDTO> getAllCategories();

    CategoriesDTO getCategoryById(int id);

    CategoriesDTO createCategory(CategoriesDTO category);

    CategoriesDTO updateCategory(int id, CategoriesDTO category);

    void deleteCategory(int id);

}
