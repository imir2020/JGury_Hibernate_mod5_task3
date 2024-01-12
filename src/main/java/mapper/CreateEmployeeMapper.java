package mapper;


import dto.ToBaseEmployeeDto;
import entity.Employees;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateEmployeeMapper {
    private static final CreateEmployeeMapper INSTANCE = new CreateEmployeeMapper();

    public Employees mapFrom(ToBaseEmployeeDto employeesDto) {
        return DtoToEmployeeMapper.INSTANCE.fromDto(employeesDto);
    }

    public static CreateEmployeeMapper getInstance() {
        return INSTANCE;
    }

}
