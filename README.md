# Desafio de Automação API Rest 

Os testes serão realizados na API Rest do Mantis Bug Tracker.

**Documentação Postman:**
- [Mantis Bug Tracker REST API](https://documenter.getpostman.com/view/29959/mantis-bug-tracker-rest-api/7Lt6zkP#intro)

**Tecnologias Utilizadas**
- Docker
- Java 8
- RestAssured
- TestNg
- MySql


# Metas


  - [x] **1)** Implementar 50 scripts de testes que manipulem uma aplicação cuja interface é uma API REST.
 > Foram criados divididos em classes de acordo com o Verbo, localizadas no package nomeado "tests".
Testes dos seguintes endpoints: 


- [X] **Filters** - **8 Testes**
<br> GetFiltersTests	5 
<br> DelIFiltersTests	3 
- [X] **Issues** - **11 Testes**
<br> PostIssuesTests	5
<br> PostIssuesFilesTests	1
<br> GetIssuesTests	2
<br> PatchIssuesTests	3
- [X] **Users** - **13 Testes**
<br> PostUserTests	8
<br> GetUserTests	2
<br> DelUserTests	3
- [X] **Projects** - **20 Testes**
<br> PostProjectsTests	3
<br> PostProjectsVersionTests	3
<br> PostSubProjectsTests	3
<br> GetProjectsTests	2
<br> PatchProjectsTests	2
<br> PatchSubProjectsTests	3
<br> DelProjectsTests	2
<br> DelSubProjectsTests	2
 
 > Dos quais realizo validação de parâmetros obrigatórios, parâmetros de entrada válidos, parâmetros de entrada inválidos, payload da response, HTTP Status Code etc.
Testes distribuídos em classes localizadas no package tests. Conforme documentação: [Mantis Bug Tracker REST API](https://documenter.getpostman.com/view/29959/mantis-bug-tracker-rest-api/7Lt6zkP#intro).

 - [x] **2)** Alguns scripts devem ler dados de uma planilha Excel para implementar Data-Driven.
 - [x] **3)** Notem que 50 scripts podem cobrir mais de 50 casos de testes se usarmos Data-Driven. Em outras palavras, implementar 50 CTs usando data-driven não é a mesma coisa que implementar 50 scripts.
 > Consta na classe de teste PostUsersTests/cadastrarUsuariosDataDrivenTest realizando a leitura do arquivo .CSV que estão no caminho src/test/java/resources/postAddUser.csv

 - [x] **4)** O projeto deve tratar autenticação.
> O token para utilização da API é gerado pelo usuário administrador no painel Tokens API em sua conta e este Token é passado no Header da requisição, conforme a documentação da API Mantis.

 - [x] **5)** Pelo menos um teste deve fazer a validação usando REGEX (Expressões Regulares).
> Na classe PostUserTests, o metodo cadastrarUsuarioValidacaoRegexEmail()

 - [x] **6)** Pelo menos um script deve usar código Groovy / Node.js ou outra linguagem para fazer scripts.
> Utilizei sql para inserções deleções e consultas.Toda automação criada em Java.

 - [x] **7)** O projeto deverá gerar um relatório de testes automaticamente.
> Ao final da execução dos testes é salvo um relatório na pasta \target\reports. O relatório é configurado pela classe ExtentReportsUtils.class e chamado nos métodos @BeforeSuite, @BeforeMethod, @AfterMethod e @AfterSuite da classe TestBase.class para que seja criado em cada execução de forma automática.

 - [x] **8)** Implementar pelo menos dois ambientes (desenvolvimento / homologação)
> Os ambientes de DEV e HML foram implementados no projeto. Parametrizados dentro da globalParameters.properties e apontados no enviroment ainda dentro deste mesmo arquivo.

 - [x] **9)** A massa de testes deve ser preparada neste projeto, seja com scripts carregando massa nova no BD ou com restore de banco de dados.
> Utilizado os parâmetros da classe DButils.class  para conexão e execução das queries, e Configurado o banco em globalParameters.properties.
sendo assim a massa de dados foi tratada através da criação de chamadas para execução antes de alguns testes, como por exemplo, excluir e cadastrar projetos e Sub Projetos antes de inicar e ao finalizar alguns testes , @BeforeSuite @AfterSuite . 
A classes que chamam as queries fica na pasta dbsteps e os arquivos .sql estão salvos em queries.

 - [x] **10)** Executar testes em paralelo. Pelo menos duas threads (25 testes cada).
 > Dentro do pom.xml, definimos o caminho para salvar a SuiteTests.XML, dentro do arquivo SuitesTests, podemos definir o numero de threads que podemos executar de forma simultânea pelo thread-count="2".

 - [x] **11)** Testes deverão ser agendados pelo Jenkins, CircleCI, TFS ou outra ferramenta de CI que preferir.
> Utilizei o GIT Hub Actions para fazer o processo de CI/CD, a execução de todos os testes ocorrem, ao realizar o push na branch master.
> Configurei o arquivo "maven.yml" , para realizar o checkout dos fontes, definir versão do java, start do container docker do arquivo "docker-compose.yml",  e os testes rodam atraves do maven (mvn -B test --file pom.xml)
