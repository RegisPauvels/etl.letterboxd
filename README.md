# ETL Letterboxd 🎬

Este projeto é um **ETL (Extract, Transform, Load)** desenvolvido em **Java + Maven + Spring Boot** para extração, transformação e carga de dados sobre filmes e diretores.  

Ele foi criado como parte da disciplina **Recuperação, Integração e Reuso de Dados (TSI - UTFPR-TD)**.

---

## 📌 Objetivo
Automatizar a coleta de informações de filmes e diretores a partir do **Letterboxd** e do **TheMovieDB**, traduzir os textos de sinopses e disponibilizar os dados processados em um **MongoDB Repository**.

---

## ⚙️ Arquitetura ETL

### 🔹 Extração
- **Filmes** via Letterboxd:
  - Título
  - Ano de lançamento
  - Avaliação
  - Elenco
  - Sinopse
  - Link para informações adicionais  

- **Diretores** via TheMovieDB (usando o link obtido no Letterboxd):
  - Nome completo
  - Biografia
  - Data de nascimento
  - Local de nascimento
  - Alcunhas  

🔧 Tecnologias utilizadas na extração:
- [**Jsoup**](https://jsoup.org/) → raspagem de HTML.  
- [**Selenium WebDriver**](https://www.selenium.dev/) → automação de navegação em páginas dinâmicas.  

---

### 🔹 Transformação
- **DeepL API** → Tradução das sinopses dos filmes para o português.  

---

### 🔹 Carga
- Armazenamento em **MongoDB**, utilizando **Spring Data MongoDB Repository**.  

---

## 🏗️ Estrutura do Projeto
A arquitetura do ETL é baseada e herdada de outro projeto de minha autoria chamado **etl.simples** (ainda não publicado no GitHub, mas será futuramente).  

---

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Maven**
- **Spring Boot**
- **Jsoup**
- **Selenium WebDriver**
- **DeepL API**
- **MongoDB**

---

## 🚀 Como Executar

### Pré-requisitos
- [Java 17+](https://adoptium.net/)  
- [Maven](https://maven.apache.org/)  
- [MongoDB](https://www.mongodb.com/) em execução local ou remoto  
- Chave de API válida do [DeepL](https://www.deepl.com/pro-api)  

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/RegisPauvels/etl.letterboxd.git
2. Acesse a pasta:
   ```bash
    cd etl.letterboxd
3. Configure as variáveis de ambiente no application.properties:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/etl_letterboxd
   deepl.api.key=SUA_CHAVE_AQUI
4. Compile e execute:
    ```bash
    mvn spring-boot:run

    
### 📖 Notas

Planejo disponibilizar o repositório etl.simples futuramente, para demonstrar a base da arquitetura ETL utilizada aqui.

👨‍🎓 Autor

Projeto desenvolvido por Regis Vinicius
Disciplina: Recuperação, Integração e Reuso de Dados
