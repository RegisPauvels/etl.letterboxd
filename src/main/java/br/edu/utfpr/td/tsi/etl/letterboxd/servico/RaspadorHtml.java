package br.edu.utfpr.td.tsi.etl.letterboxd.servico;



import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.etl.letterboxd.modelo.Diretor;
import br.edu.utfpr.td.tsi.etl.letterboxd.modelo.Filme;
import br.edu.utfpr.td.tsi.etl.simples.Extrator;


@Component
public class RaspadorHtml extends Extrator<Filme>{
	
	Logger logger = LoggerFactory.getLogger(RaspadorHtml.class);
	
	@Autowired
	private Navegador navegador;
	
	private String prefixo = "https://letterboxd.com";
	
	@Value("${carga}")
	private int carga;
	

	@Override
	public List<Filme> extrair(){
		
		logger.atLevel(Level.INFO).setMessage("Iniciando raspagem Letterboxd").log();
		
		List<Filme> listaFilmes = new ArrayList<Filme>();
		
		while(carga > 0) {
			if(carga == 1) {
				Document doc = Jsoup.parse(navegador.carregarHtml("https://letterboxd.com/films/popular", ".film-poster a"), prefixo);
				
				Elements filmes = doc.select(".film-poster a");
				
			    for (int i = 0; i < 10; i++) {
			        Element filme = filmes.get(i);
			        String link = filme.attr("abs:href");
			        listaFilmes.add(rasparPaginaFilme(link, i));
			    }
			    
			}else {
				Document doc = Jsoup.parse(navegador.carregarHtml("https://letterboxd.com/films/popular/page/"+carga, ".film-poster a"), prefixo);
				
				Elements filmes = doc.select(".film-poster a");
			    for (int i = 0; i < filmes.size(); i++) {
			        Element filme = filmes.get(i);
			        String link = filme.attr("abs:href");
			        listaFilmes.add(rasparPaginaFilme(link, i));
			    }
			}
			
			carga--;
		}
		
		return listaFilmes;
	}
	
	public Filme rasparPaginaFilme(String link, int i) {
		logger.atLevel(Level.INFO).setMessage("Iniciando raspagem Filme "+(i+1)).log();
		
		Document doc = Jsoup.parse(navegador.carregarHtml(link, ".film-poster img"));
		
		String titulo = doc.selectFirst(".details .name").text();
		
		int anoLancamento = Integer.parseInt(doc.selectFirst(".productioninfo .releasedate a").text());
		
		String sinopse = doc.selectFirst(".production-synopsis .truncate p").text();
		
		double avaliacao = Double.parseDouble(doc.selectFirst(".ratings-histogram-chart .display-rating").text());
		
		
		List<String> listaElenco = new ArrayList<String>();
		Elements elenco = doc.select("#tab-cast .cast-list p .tooltip");
		for (Element element : elenco) {
			listaElenco.add(element.text());
		}
		
		String linkDiretor = prefixo + doc.selectFirst(".credits .contributor").attr("href");
		
		Diretor diretor = rasparPaginaDiretor(linkDiretor);
		
		Filme filme = new Filme(null, titulo, diretor, anoLancamento, sinopse, avaliacao, listaElenco);
		
		return filme;
	}
	
	
	public Diretor rasparPaginaDiretor(String link) {
		logger.atLevel(Level.INFO).setMessage("Iniciando raspagem Diretor").log();
		
		Document doc = Jsoup.parse(navegador.carregarPosNavegacao(link, ".bio .text-link a"));
		
		String nome = doc.selectFirst(".title a").text();
		String biografia = "Biografia nao encontrada";
		
		Element bio = doc.selectFirst(".biography .text p");
		if(bio != null) {
			biografia = doc.selectFirst(".biography .text p ").text();
		}
		
		String genero = "";
		String dataNascimento = "";
		String localNascimento = "";
		Elements infos = doc.select(".facts p");
		for (Element element : infos) {
			if(element.select("strong").text().contains("Gênero")) {
				genero = element.text().split(" ")[1];
			}
			
			if(element.select("strong").text().contains("Nascimento")) {
				dataNascimento = element.text().split("Nascimento ")[1];
			}
			
			if(element.select("strong").text().contains("Local de nascimento")) {
				localNascimento = element.text().split("\\) ")[1];
			}
		}
		
		List<String> listaAlcunhas = new ArrayList<String>();
		Elements alcunhas = doc.select(".facts ul li");
		for (Element element : alcunhas) {
			listaAlcunhas.add(element.text());
		}
		
		Diretor diretor = new Diretor(null, nome, biografia, genero, dataNascimento, localNascimento, listaAlcunhas);
		
		return diretor;
		
	}
	
}
