package br.edu.utfpr.td.tsi.etl.letterboxd.modelo;

import java.util.List;

public class Diretor {
	private String id;
	private String nomeCompleto;
	private String biografia;
	private String genero;
	private String dataNascimento;
	private String localNascimento;
	private List<String> alcunhas;
	
	
	
	public Diretor(String id, String nomeCompleto, String biografia, String genero, String dataNascimento,
			String localNascimento, List<String> alcunhas) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.biografia = biografia;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.localNascimento = localNascimento;
		this.alcunhas = alcunhas;
	}
	
	
	public Diretor() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getLocalNascimento() {
		return localNascimento;
	}
	public void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}
	public List<String> getAlcunhas() {
		return alcunhas;
	}
	public void setAlcunhas(List<String> alcunhas) {
		this.alcunhas = alcunhas;
	}
	
	
}
