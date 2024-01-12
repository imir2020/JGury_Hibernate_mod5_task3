package service;

import dao.CategoryRepository;
import dto.CategoryDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService INSTANCE = new CategoryService();

    public static CategoryService getInstance(){
        return INSTANCE;
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(category ->
                        new CategoryDto(category.getId(),
                                category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
