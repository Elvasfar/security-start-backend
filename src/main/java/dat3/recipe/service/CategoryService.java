package dat3.recipe.service;

import dat3.recipe.dto.CategoryDto;
import dat3.recipe.dto.RecipeDto;
import dat3.recipe.entity.Category;
import dat3.recipe.entity.Recipe;
import dat3.recipe.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllCategories() {
        List<Category> categories =  categoryRepository.findAll();
        //Convert from list of Categories to DTO-type, list of Strings
        return categories.stream().map(Category::getName).toList();    }

    public CategoryDto addCategory(CategoryDto request) {
             Category newCategory = new Category();
        updateCategory(newCategory, request);
        categoryRepository.save(newCategory);
        return new CategoryDto(newCategory,false);
    }

    private void updateCategory(Category original, CategoryDto c) {
        original.setName(c.getName());
    }

}
