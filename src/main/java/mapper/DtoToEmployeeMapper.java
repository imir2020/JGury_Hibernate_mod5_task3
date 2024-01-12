package mapper;

import dto.ToBaseEmployeeDto;
import entity.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToEmployeeMapper {
    DtoToEmployeeMapper INSTANCE = Mappers.getMapper(DtoToEmployeeMapper.class);

    Employees fromDto(ToBaseEmployeeDto employeeDto);
}
