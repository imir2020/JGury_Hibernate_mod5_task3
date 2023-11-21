package entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Orders {
    private Long id;
    private Long supplierId;
    private String nameProduct;
    private Long countProduct;
    private Long priceProduct;
    private LocalDate dateOrder;
}
