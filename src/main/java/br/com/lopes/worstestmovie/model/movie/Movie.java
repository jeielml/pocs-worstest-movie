package br.com.lopes.worstestmovie.model.movie;

import br.com.lopes.worstestmovie.model.producer.Producer;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Integer year;
    @NonNull
    private String title;
    @NonNull
    private String studios;

    @ManyToMany
    @NonNull
    private List<Producer> producers;
    private boolean winner;
}
