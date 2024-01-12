package entity;

import javax.persistence.*;

import dao.Greid;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ranks")
public class Ranks implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank_name")
    private Greid rankName;

    @Column(name = "salary")
    private Long salary;
}
