package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Ranks {
    private Long id;
    private String rankName;
    private Long salary;
}
