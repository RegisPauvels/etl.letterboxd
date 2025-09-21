package br.edu.utfpr.td.tsi.etl.letterboxd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.utfpr.td.tsi.etl.letterboxd.modelo.Filme;
import br.edu.utfpr.td.tsi.etl.letterboxd.persistencia.FilmeDao;
import br.edu.utfpr.td.tsi.etl.simples.CarregadorMongoDb;
import br.edu.utfpr.td.tsi.etl.simples.Extrator;
import br.edu.utfpr.td.tsi.etl.simples.Job;
import br.edu.utfpr.td.tsi.etl.simples.Transformador;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Main {
	@Autowired
	private FilmeDao repository;
	
	@Autowired
	Extrator<Filme> extrator;
	
	@Autowired
	Transformador<Filme, Filme> transformador;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		CarregadorMongoDb<Filme, String> carregador = new CarregadorMongoDb<Filme, String>();
		
		carregador.setRepository(repository);
		
		Job<Filme, Filme> job = new Job<Filme, Filme>();
		job.setExtrator(extrator);
		job.setTransformador(transformador);
		job.setCarregador(carregador);
		job.executar();
	}
	
}
