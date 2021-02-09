package br.com.lopes.worstestmovie.model.producer;


import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "producers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@EqualsAndHashCode(of = {"id"})
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy="producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Win> wins;

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
