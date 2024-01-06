package entity;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "supplier_id")
    private Suppliers supplier;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Long count;

    @Column(name = "price_for_one")
    private Long priceForOne;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
