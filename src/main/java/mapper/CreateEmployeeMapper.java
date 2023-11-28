package mapper;

import dto.EmployeesDto;
import dto.ToBaseEmployeeDto;
import entity.Employees;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.LocalDateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateEmployeeMapper implements Mapper<Employees, ToBaseEmployeeDto> {
    private static final CreateEmployeeMapper INSTANCE = new CreateEmployeeMapper();
    @Override
    public Employees mapFrom(ToBaseEmployeeDto employeesDto) {
        return Employees.builder()
                .lastName(employeesDto.getLastName())
                .name(employeesDto.getName())
                .middleName(employeesDto.getMiddleName())
                .dateBirth(LocalDateFormatter.format(employeesDto.getDateBirth()))
                .phoneNumber(employeesDto.getPhoneNumber())
                .address(employeesDto.getAddress())
                .rankId(employeesDto.getRankId())
                .build();
    }

    public static CreateEmployeeMapper getInstance(){
        return INSTANCE;
    }
}
