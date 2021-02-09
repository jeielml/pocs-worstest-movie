package br.com.lopes.worstestmovie.model.producer;

import org.springframework.data.repository.CrudRepository;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    public Producer findByName(String name);
}
