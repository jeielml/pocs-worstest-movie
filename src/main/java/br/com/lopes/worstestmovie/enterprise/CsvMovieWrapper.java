package br.com.lopes.worstestmovie.enterprise;

import br.com.lopes.worstestmovie.model.movie.Movie;
import br.com.lopes.worstestmovie.model.producer.Producer;
import br.com.lopes.worstestmovie.model.producer.ProducerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Producer> producer = getProducers(producerService);

        return Movie.builder()
                .year(this.year)
                .title(this.title)
                .studios(this.studios)
                .producer(producer.get(0)) //TODO: transformar em lista
                .winner(won())
                .build();
    }

    private List<Producer> getProducers(ProducerService producerService) {
        List<String> producersName = Arrays.stream(this.producers.split(" and "))
                .flatMap(splited -> Arrays.stream(splited.split(",")).map(String::trim))
                .collect(Collectors.toList());

        return producersName.stream()
                .map(producerName -> getProducer(producerService, producerName))
                .collect(Collectors.toList());
    }

    private Producer getProducer(ProducerService producerService, String producerName) {
        Producer producer = producerService.findByName(producerName);
        if (won()) {
            producer = producerService.updateWins(producer, this.year);
        }
        return producer;
    }

    private boolean won() {
        return "yes".equals(this.winner);
    }
}
