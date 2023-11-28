package service;


import dao.EmployeesDao;
import dto.EmployeesDto;
import dto.ToBaseEmployeeDto;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import mapper.CreateEmployeeMapper;
import mapper.EmployeeMapper;
import validator.EmployeeValidator;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EmployeeService {
    private static final EmployeeService INSTANCE = new EmployeeService();
    private final EmployeesDao employeesDao = EmployeesDao.getInstance();
    private final EmployeeMapper employeeMapper = EmployeeMapper.getInstance();
    private final EmployeeValidator employeeValidator = EmployeeValidator.getInstance();
    private final CreateEmployeeMapper createEmployeeMapper = CreateEmployeeMapper.getInstance();


    public List<EmployeesDto> findAll() {
        return employeesDao.findAll().stream().map(employeeMapper::mapFrom)
                .collect(Collectors.toList());

    }
    public Long saveEmployee(ToBaseEmployeeDto toBaseEmployeeDto){
        var validationResult = employeeValidator.isValid(toBaseEmployeeDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var user = createEmployeeMapper.mapFrom(toBaseEmployeeDto);
        var result = employeesDao.save(user);
        return result.getId();
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }

}
