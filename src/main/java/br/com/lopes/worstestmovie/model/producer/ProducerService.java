package br.com.lopes.worstestmovie.model.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeSet;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private WinRepository winRepository;

    public Producer findByName(String producerName) {
        return Optional.ofNullable(producerRepository.findByName(producerName))
                .orElseGet(() -> {
                    Producer build = Producer.builder()
                            .name(producerName)
                            .wins(new TreeSet<>())
                            .build();
                    return producerRepository.save(build);
                });
    }

    public Producer updateWins(Producer producer, Integer year) {
        Win build = Win.builder().year(year)
                .producer(producer)
                .build();
        winRepository.save(build);
        return producerRepository.findByName(producer.getName());
    }
}
