package entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
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
