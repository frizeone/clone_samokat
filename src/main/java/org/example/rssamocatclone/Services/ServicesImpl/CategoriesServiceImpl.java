package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.CategoriesMapper;
import org.example.rssamocatclone.Services.CategoriesService;
import org.example.rssamocatclone.dto.CategoriesDTO;
import org.example.rssamocatclone.models.Categories;
import org.example.rssamocatclone.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    // Получить все категории
    public List<CategoriesDTO> getAllCategories() {
        return categoriesRepository.findAll().stream()
                .map(CategoriesMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Получить категорию по ID
    public CategoriesDTO getCategoryById(int id) {
        Optional<Categories> category = categoriesRepository.findById(id);
        return category.map(CategoriesMapper::toDTO).orElse(null);
    }

    // Создать новую категорию
    public CategoriesDTO createCategory(CategoriesDTO categoriesDTO) {
        Categories category = CategoriesMapper.toEntity(categoriesDTO);
        Categories savedCategory = categoriesRepository.save(category);
        return CategoriesMapper.toDTO(savedCategory);
    }

    // Обновить существующую категорию
    public CategoriesDTO updateCategory(int id, CategoriesDTO categoriesDTO) {
        Optional<Categories> categoryOptional = categoriesRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Categories category = categoryOptional.get();
            category.setName(categoriesDTO.getName());
            category.setDescription(categoriesDTO.getDescription());
            categoriesRepository.save(category);
            return CategoriesMapper.toDTO(category);
        }
        return null;  // В реальном приложении можно вернуть исключение
    }

    // Удалить категорию по ID
    public void deleteCategory(int id) {
        categoriesRepository.deleteById(id);
    }
}
