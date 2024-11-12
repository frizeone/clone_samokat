package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.CategoriesService;
import org.example.rssamocatclone.dto.CategoriesDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final CategoriesService categoriesService;

    public CategoriesController(RabbitTemplate rabbitTemplate, CategoriesService categoriesService) {
        this.rabbitTemplate = rabbitTemplate;
        this.categoriesService = categoriesService;
    }


    @GetMapping
    public List<CategoriesDTO> getAllCategories() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод getAllCategories]");
        return categoriesService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDTO> getCategoryById(@PathVariable int id) {
        CategoriesDTO category = categoriesService.getCategoryById(id);
        if (category != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод getCategoryById][+]" + category.logMethod());
            return ResponseEntity.ok(category);
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод getCategoryById][-]" + category.logMethod());
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<CategoriesDTO> createCategory(@RequestBody CategoriesDTO categoriesDTO) {
        CategoriesDTO createdCategory = categoriesService.createCategory(categoriesDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод createCategory]" + createdCategory.logMethod());
        return ResponseEntity.ok(createdCategory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDTO> updateCategory(@PathVariable int id, @RequestBody CategoriesDTO categoriesDTO) {
        CategoriesDTO updatedCategory = categoriesService.updateCategory(id, categoriesDTO);
        if (updatedCategory != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод updateCategory][+]" + updatedCategory.logMethod());
            return ResponseEntity.ok(updatedCategory);
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод updateCategory][-]" + updatedCategory.logMethod());
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoriesService.deleteCategory(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CategoriesController][Сработал метод updateCategory][Удалена категория с id = {" + id + "}]");
        return ResponseEntity.noContent().build();
    }
}
