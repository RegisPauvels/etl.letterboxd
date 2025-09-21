package br.edu.utfpr.td.tsi.etl.letterboxd.servico;

import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Navegador {
	
	@Value("${chromeBinary}")
	private String chromeBinary;
	
	ChromeOptions options = new ChromeOptions();
	
	public String carregarHtml(String link, String contexto) {
		
		options.setBinary(chromeBinary);
		options.addArguments("--headless=new");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.addArguments("--blink-settings=imagesEnabled=false");
		
		WebDriver driver = new ChromeDriver(options);
		String html = "";
		try {
			driver.get(link);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(contexto)));
			
			html = driver.getPageSource();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			driver.quit();
			
		}
		
		return html;
	}
	
	public String carregarPosNavegacao(String link, String contexto) {
		options.setBinary(chromeBinary);
		options.addArguments("--headless=new");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.addArguments("--blink-settings=imagesEnabled=false");
		
		WebDriver driver = new ChromeDriver(options);
		String html = "";
		try {
			driver.get(link);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(contexto)));
			
			Document doc = Jsoup.parse(driver.getPageSource());
			String proxLink = doc.selectFirst(contexto).attr("href");
			
			driver.navigate().to(proxLink);
			
			html = driver.getPageSource();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			driver.quit();
			
		}
		
		return html;
	}
}
