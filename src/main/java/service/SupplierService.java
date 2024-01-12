package service;

import dao.SuppliersRepository;
import dto.SuppliersDto;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SupplierService {
    private final SuppliersRepository suppliersRepository = new SuppliersRepository();
    private static final SupplierService INSTANCE = new SupplierService();

    public static SupplierService getInstance() {
        return INSTANCE;
    }


    public List<SuppliersDto> findAll() {
        return suppliersRepository.findAll().stream().map(suppliers ->
                        new SuppliersDto(suppliers.getId(),
                                ("%s| %s| %s| %s ").formatted(
                                        suppliers.getName(),
                                        suppliers.getAddress(),
                                        suppliers.getEmail(),
                                        suppliers.getPhoneNumber())))
                .collect(Collectors.toList());

    }
}
