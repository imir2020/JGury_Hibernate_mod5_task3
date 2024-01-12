package service;


import dao.EmployeesRepository;
import dto.EmployeesDto;
import dto.ToBaseEmployeeDto;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import mapper.CreateEmployeeMapper;
import mapper.EmployeeMapper;
import validator.EmployeeValidator;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EmployeeService {
    private static final EmployeeService INSTANCE = new EmployeeService();
    private final EmployeesRepository employeesRepository = new EmployeesRepository();
    private final EmployeeMapper employeeMapper = EmployeeMapper.getInstance();
    private final EmployeeValidator employeeValidator = EmployeeValidator.getInstance();
    private final CreateEmployeeMapper createEmployeeMapper = CreateEmployeeMapper.getInstance();


    public List<EmployeesDto> findAll() {
        return employeesRepository.findAll().stream().map(employeeMapper::mapFrom)
                .collect(Collectors.toList());

    }
    public Long saveEmployee(ToBaseEmployeeDto toBaseEmployeeDto){
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(toBaseEmployeeDto);
        if(!validationResult.isEmpty()){
            throw new ConstraintViolationException(validationResult);
        }
        var user = createEmployeeMapper.mapFrom(toBaseEmployeeDto);
        var result = employeesRepository.save(user);
        return result.getId();
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }

}
