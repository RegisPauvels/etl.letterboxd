package br.edu.utfpr.td.tsi.etl.letterboxd.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Filme {
	@Id
	private String id;
	private String titulo;
	private Diretor diretor;
	private int anoLancamento;
	private String sinopse;
	private double avaliacao;
	private List<String> elenco;
	

	public Filme(String id, String titulo, Diretor diretor, int anoLancamento, String sinopse, double avaliacao,
			List<String> elenco) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.diretor = diretor;
		this.anoLancamento = anoLancamento;
		this.sinopse = sinopse;
		this.avaliacao = avaliacao;
		this.elenco = elenco;
	}
	
	
	public Filme() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Diretor getDiretor() {
		return diretor;
	}
	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public List<String> getElenco() {
		return elenco;
	}
	public void setElenco(List<String> elenco) {
		this.elenco = elenco;
	}
	
	
}
