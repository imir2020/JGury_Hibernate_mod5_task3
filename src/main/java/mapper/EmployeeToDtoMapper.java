package mapper;

import dto.EmployeesDto;
import entity.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeToDtoMapper {
    EmployeeToDtoMapper INSTANCE = Mappers.getMapper(EmployeeToDtoMapper.class);

    EmployeesDto employeesToDto(Employees employee);
}
