package br.com.lopes.worstestmovie.enterprise;

import antlr.StringUtils;
import br.com.lopes.worstestmovie.model.movie.Movie;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CsvMovieWrapper {
    @NonNull
    private Integer year;
    @NonNull
    private String title;
    @NonNull
    private String studios;
    @NonNull
    private String producers;
    private String winner;

    public Movie toEntity() {
        return Movie.builder()
                .year(this.year)
                .title(this.title)
                .studios(this.studios)
                .producers(this.producers)
                .winner("yes".equals(this.winner))
                .build();
    }
}
