package service;

import dao.SuppliersDao;
import dto.SuppliersDto;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierService {
    private static final SupplierService INSTANCE = new SupplierService();
    private final SuppliersDao supplierDao = SuppliersDao.getInstance();

    private SupplierService() {
    }

    public static SupplierService getInstance() {
        return INSTANCE;
    }

    public List<SuppliersDto> findAll() {
        return supplierDao.findAll().stream().map(suppliers ->
                        new SuppliersDto(suppliers.getId(),
                                ("%s: %s: %s: %s: ").formatted(
                                        suppliers.getName(),
                                        suppliers.getAddress(),
                                        suppliers.getEmail(),
                                        suppliers.getPhoneNumber())))
                .collect(Collectors.toList());

    }
}
