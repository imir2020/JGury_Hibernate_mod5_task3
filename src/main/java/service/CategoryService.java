package service;

import dao.CategoryDao;
import dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private static final CategoryService INSTANCE = new CategoryService();
    private final CategoryDao categoryDao = CategoryDao.getInstance();

    private CategoryService() {
    }

    public static CategoryService getInstance() {
        return INSTANCE;
    }

    public List<CategoryDto> findAll() {
        return categoryDao.findAll().stream().map(category ->
                        new CategoryDto(category.getCategory(),
                                category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
