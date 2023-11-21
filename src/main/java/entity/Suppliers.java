package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Suppliers {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
}
