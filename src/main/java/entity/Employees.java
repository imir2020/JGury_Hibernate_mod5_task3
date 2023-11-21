package entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Employees {
    private Long id;
    private String lastName;
    private String name;
    private String middleName;
    private LocalDate dateBirth;
    private String phoneNumber;
    private String address;
    private Long rankId;

}
