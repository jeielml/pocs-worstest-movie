package br.com.lopes.worstestmovie.controllers;

import br.com.lopes.worstestmovie.enterprise.RecordNotFoundException;
import br.com.lopes.worstestmovie.model.movie.Movie;
import br.com.lopes.worstestmovie.model.movie.MovieRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping
    public List<Movie> findAll() {
        return (List<Movie>) this.repository.findAll();
    }

    /**
     * FIXME: return friendly not found message
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @NotFound(action = NotFoundAction.IGNORE)
    public Movie find(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This movie does not exists!"));
    }


}
