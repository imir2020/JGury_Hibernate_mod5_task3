package mapper;

import dto.EmployeesDto;
import entity.Employees;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {
    private static final EmployeeMapper INSTANCE = new EmployeeMapper();

    public EmployeesDto mapFrom(Employees employee) {
        return EmployeeToDtoMapper.INSTANCE.employeesToDto(employee);
    }

    public static EmployeeMapper getInstance() {
        return INSTANCE;
    }
}
