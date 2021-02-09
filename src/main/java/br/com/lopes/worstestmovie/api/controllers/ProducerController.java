package br.com.lopes.worstestmovie.api.controllers;

import br.com.lopes.worstestmovie.api.representation.TopTailWinnersInterval;
import br.com.lopes.worstestmovie.api.representation.WinnerInterval;
import br.com.lopes.worstestmovie.enterprise.RecordNotFoundException;
import br.com.lopes.worstestmovie.model.producer.Producer;
import br.com.lopes.worstestmovie.model.producer.ProducerRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/producers")
public class ProducerController {

    @Autowired
    private ProducerRepository repository;

    @GetMapping
    public List<Producer> findAll() {
        return (List<Producer>) this.repository.findAll();
    }

    @GetMapping("{id}")
    @NotFound(action = NotFoundAction.IGNORE)
    public Producer find(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This producer does not exists!"));
    }

    @GetMapping("winners")
    public List<Producer> findWinners() {
        return ((List<Producer>) this.repository.findAll())
                .stream()
                .filter(producer -> !producer.getWins().isEmpty())
                .collect(Collectors.toList());
    }

    @GetMapping("winners/intervals")
    public Set<WinnerInterval> findWinnersIntervals() {
        return ((List<Producer>) this.repository.findAll())
                .stream()
                .filter(producer -> !producer.getWins().isEmpty() && producer.getWins().size() > 1)
                .flatMap(producer -> producer.generateWinnerIntervals().stream())
                .collect(Collectors.toSet());
    }

    @GetMapping("winners/intervals/top-tail-awards")
    public TopTailWinnersInterval findTopTailWinners() {
        Set<WinnerInterval> winners = ((List<Producer>) this.repository.findAll())
                .stream()
                .filter(producer -> producer.getWins().size() > 1)
                .flatMap(producer -> producer.generateWinnerIntervals().stream())
                .collect(Collectors.toSet());

        return TopTailWinnersInterval.fromWinnersInterval(winners);
    }



}
