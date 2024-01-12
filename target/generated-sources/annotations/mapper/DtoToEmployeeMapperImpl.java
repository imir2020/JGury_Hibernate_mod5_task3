package mapper;

import dto.ToBaseEmployeeDto;
import entity.Employees;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-12T17:31:19+0300",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToEmployeeMapperImpl implements DtoToEmployeeMapper {

    @Override
    public Employees fromDto(ToBaseEmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employees.EmployeesBuilder employees = Employees.builder();

        employees.lastName( employeeDto.getLastName() );
        employees.name( employeeDto.getName() );
        employees.middleName( employeeDto.getMiddleName() );
        if ( employeeDto.getDateBirth() != null ) {
            employees.dateBirth( LocalDate.parse( employeeDto.getDateBirth() ) );
        }
        employees.phoneNumber( employeeDto.getPhoneNumber() );
        employees.address( employeeDto.getAddress() );

        return employees.build();
    }
}
