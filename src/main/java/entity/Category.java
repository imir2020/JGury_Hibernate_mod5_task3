package entity;


import lombok.*;
import service.CategoryService;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Category {
    private Long category;
    private String categoryName;
}
