package io.github.aaronr92.pcstore.service;

import io.github.aaronr92.pcstore.entity.Category;
import io.github.aaronr92.pcstore.exception.CategoryNotFoundException;
import io.github.aaronr92.pcstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Ищет в базе данных указанную Категорию по ее идентификатору
     * @param id идентификатор для поиска
     * @return найденный в бд
     * @throws CategoryNotFoundException если категория не существует в базе данных
     */
    public Category getCategoryById(long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(
                CategoryNotFoundException::new
        );
    }

    /**
     * Находит все категории, существующие в базе данных
     * @return все категории в бд
     */
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    /**
     * Сохраняет новую категорию в базе данных
     * @param category для сохранения
     * @return сохраненная категория
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
