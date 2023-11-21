package entity;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Sales {
    private Long id;
    private Long productId;
    private Long count;
    private Long employeeId;
    private LocalDate dateSales;
}
