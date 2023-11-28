package mapper;

import dto.EmployeesDto;
import entity.Employees;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper implements Mapper<EmployeesDto, Employees> {
    private static final EmployeeMapper INSTANCE = new EmployeeMapper();
    @Override
    public EmployeesDto mapFrom(Employees employee) {
        return EmployeesDto.builder()
                .id(employee.getId())
                .lastName(employee.getLastName())
                .name(employee.getName())
                .dateBirth(employee.getDateBirth())
                .phoneNumber(employee.getPhoneNumber())
                .rankId(employee.getRankId())
                .build();
    }

    public static EmployeeMapper getInstance(){
        return INSTANCE;
    }
}
