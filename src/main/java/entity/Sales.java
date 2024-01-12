package entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sales")
public class Sales implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "count")
    private Long count;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employees employee;

    @Column(name = "date_sales")
    private LocalDate dateSales;
}
