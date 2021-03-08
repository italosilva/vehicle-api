<h1 align="center">Vehicle-API</h1>

<p align="center">Está API tem como proposta gerenciar dados dos veículos.</p>

<img src="https://img.shields.io/static/v1?label=API&message=Available&color=32CD32&style=for-the-badge&logo=ghost"/>


### Features

- [x] Criar um novo veículo
- [x] Atualizar um veículo
- [x] Deletar um veículo
- [x] Buscar todos os veículos com paginação
- [x] Buscar todos os veículos com status "true"
- [x] Buscar um veículo por placa

### Pré Requisitos

Para executar a api em sua máquina, é necessário possuir as seguintes ferramentas:
- [Git](https://git-scm.com) - Para realizar o clone do projeto.
- [Apache Maven](https://maven.apache.org/) - Para realizar o download das dependencias e realizar o build.
- [Java 15](https://www.oracle.com/java/technologies/javase/15-0-2-relnotes.html) - Para executar api em sua máquina.
- [Docker](https://www.docker.com/) - Para interagir com a banco de dados Postgres SQL.

Além das ferramentas, é bom ter um editor para trabalhar com o código como [Eclipse](hhttps://www.eclipse.org/downloads/), [Spring Tools](https://spring.io/tools) ou [IntelliJ](https://www.jetbrains.com/pt-br/idea/).

### Rodando a api

```bash
# Clone o repositório
$ git clone <https://github.com/italosilva/vehicle-api>

# Acesse a pasta do projeto no terminal/cmd
$ cd vehicle-api

# Certifique-se que o Docker está executando e rode o comando para baixar a imagem do banco de dados
$ docker-compose up -d

# Instale as dependências e realize o build
$ mvn clean install

# Execute a aplicação
$ mvn spring-boot:run

# O servidor inciará na porta:8081 - acesse o Swagger da API em <http://localhost:8081/swagger-ui.html>
```

### Testes

Afim de aumentar a confiabilidade, foram executados testes unitários nas camadas de Controller, Service e Repository.

Após realizar o build, dentro da pasta do projeto, acesse "/target/site/jacoco" e abra o arquivo index.html para acompanhar a taxa de cobertura.


### Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Git](https://git-scm.com)
- [Apache Maven](https://maven.apache.org/)
- [Java 15](https://www.oracle.com/java/technologies/javase/15-0-2-relnotes.html)
- [JUnit](https://junit.org/junit5/)
- [JaCoCo](https://www.jacoco.org/jacoco/trunk/index.html)
- [Docker](https://www.docker.com/)
- [IntelliJ](https://www.jetbrains.com/pt-br/idea/)

### Autor

<a href="https://www.linkedin.com/in/italosilva-is">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/18040671?s=460&u=699310fb137e806f8673b55f673f1e021b6526eb&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Italo Silva</b></sub>
</a>


Entre em contato!
 
[![Linkedin Badge](https://img.shields.io/badge/-Italo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/italosilva-is/)](https://www.linkedin.com/in/italosilva-is/)


