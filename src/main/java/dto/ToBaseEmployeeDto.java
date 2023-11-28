package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ToBaseEmployeeDto {
    String lastName;
    String name;
    String middleName;
    String dateBirth;
    String phoneNumber;
    String address;
    Long rankId;
}
