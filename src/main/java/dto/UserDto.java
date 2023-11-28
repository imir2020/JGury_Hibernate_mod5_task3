package dto;

import entity.Status;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    Status status;
}
