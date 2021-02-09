package br.com.lopes.worstestmovie.model.movie;

import lombok.*;

import javax.persistence.*;

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
    @NonNull
    private String producers;
    private boolean winner;
}
