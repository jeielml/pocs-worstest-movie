package br.com.lopes.worstestmovie.enterprise;

import br.com.lopes.worstestmovie.model.movie.Movie;
import br.com.lopes.worstestmovie.model.producer.Producer;
import br.com.lopes.worstestmovie.model.producer.ProducerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    public Movie toEntity(ProducerService producerService) {
        Producer producer = getProducer(producerService);

        return Movie.builder()
                .year(this.year)
                .title(this.title)
                .studios(this.studios)
                .producer(producer)
                .winner(won())
                .build();
    }

    private Producer getProducer(ProducerService producerService) {
        Producer producer = producerService.findByName(this.producers);
        if (won()) {
            producer = producerService.updateWins(producer, this.year);
        }
        return producer;
    }

    private boolean won() {
        return "yes".equals(this.winner);
    }
}
