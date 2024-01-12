package entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "supplier_id")
    private Suppliers supplier;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "count_product")
    private Long countProduct;

    @Column(name = "price_product")
    private Long priceProduct;

    @Column(name = "date_order")
    private LocalDate dateOrder;

}
