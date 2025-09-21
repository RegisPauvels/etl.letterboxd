package br.edu.utfpr.td.tsi.etl.letterboxd.servico;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deepl.api.DeepLClient;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;

import br.edu.utfpr.td.tsi.etl.letterboxd.modelo.Filme;
import br.edu.utfpr.td.tsi.etl.simples.Transformador;

@Component
public class Tradutor extends Transformador<Filme, Filme>{
	
	Logger logger = LoggerFactory.getLogger(Tradutor.class);
	
	DeepLClient client;
	
    public Tradutor(@Value("${key}") String key) {
        this.client = new DeepLClient(key);
    }
		

	@Override
	public Filme transformar(Filme item) {
		logger.atLevel(Level.INFO).setMessage("Efetuando tradução das sinopses ...").log();
		TextResult resultadoTraducao = null;
		try {
			resultadoTraducao = client.translateText(item.getSinopse(), null, "pt-BR");
		} catch (DeepLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(resultadoTraducao!= null) {
			item.setSinopse(resultadoTraducao.getText());
		}
		
		return item;
	}

	@Override
	public List<Filme> transformar(List<Filme> itens) {
		logger.atLevel(Level.INFO).setMessage("Efetuando tradução das sinopses ...").log();
		
		for (Filme filme : itens) {
			TextResult resultadoTraducao = null;
			try {
				resultadoTraducao = client.translateText(filme.getSinopse(), null, "pt-BR");
			} catch (DeepLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(resultadoTraducao!= null) {
				filme.setSinopse(resultadoTraducao.getText());
			}
		}
			
		return itens;
	}

}
