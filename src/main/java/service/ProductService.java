package service;

import dao.ProductsDao;
import dto.ProductDto;

public class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    private final ProductsDao productsDao = ProductsDao.getInstance();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }

    public ProductDto findById(Long id) {
        return productsDao.findById(id).map(products ->
                new ProductDto(products.getId(),
                        ("%s-%s").formatted(
                                products.getName(),
                                String.valueOf(products.getCount())
                        ))).get();

    }
}
