package br.edu.utfpr.td.tsi.etl.letterboxd.persistencia;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.etl.letterboxd.modelo.Filme;

public interface FilmeDao extends MongoRepository<Filme, String>{

}
