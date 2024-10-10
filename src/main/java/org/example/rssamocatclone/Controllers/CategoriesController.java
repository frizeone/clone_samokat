package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.CategoriesService;
import org.example.rssamocatclone.dto.CategoriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }


    @GetMapping
    public List<CategoriesDTO> getAllCategories() {
        return categoriesService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDTO> getCategoryById(@PathVariable int id) {
        CategoriesDTO category = categoriesService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<CategoriesDTO> createCategory(@RequestBody CategoriesDTO categoriesDTO) {
        CategoriesDTO createdCategory = categoriesService.createCategory(categoriesDTO);
        return ResponseEntity.ok(createdCategory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDTO> updateCategory(@PathVariable int id, @RequestBody CategoriesDTO categoriesDTO) {
        CategoriesDTO updatedCategory = categoriesService.updateCategory(id, categoriesDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoriesService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
