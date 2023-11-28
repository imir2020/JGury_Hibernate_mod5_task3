package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EmployeesDto {
    Long id;
    String lastName;
    String name;
    String middleName;
    LocalDate dateBirth;
    String phoneNumber;
    String address;
    Long rankId;
}
