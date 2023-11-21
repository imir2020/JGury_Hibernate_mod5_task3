package service;

import dao.ProductsDao;
import dto.ProductDto;

import java.util.Optional;

public class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    private final ProductsDao productsDao = ProductsDao.getInstance();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }

    //    public ProductDto findById(Long id){
//        return new ProductDto(productsDao.findById(id).get().getId(),
//                ("%s-%s").formatted(productsDao.findById(id).get().getName(),
//               String.valueOf(productsDao.findById(id).get().getCount())));
//
//    }
    public ProductDto findById(Long id) {
        return productsDao.findById(id).map(products ->
                new ProductDto(products.getId(),
                        ("%s-%s").formatted(
                                products.getName(),
                                String.valueOf(products.getCount())
                        ))).get();

    }
}
