package service;


import dao.ProductsRepository;
import dto.ProductDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository = new ProductsRepository();
    private static final ProductService INSTANCE = new ProductService();

    public static ProductService getInstance(){
        return INSTANCE;
    }

    public ProductDto findById(Long id) {
        return productsRepository.findById(id).map(products ->
                new ProductDto(products.getId(),
                        ("%s-%s").formatted(
                                products.getName(),
                                String.valueOf(products.getCount())
                        ))).get();

    }
}
