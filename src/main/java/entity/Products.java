package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Products {
    private Long id;
    private Long supplierId;
    private String name;
    private Long count;
    private Long priceForOne;
    private Long categoryId;
}
