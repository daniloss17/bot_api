# THE BOT API

O projeto consiste em troca de mensagens de bots com usuarios anônimos

O arquivo DBSeeder já cria automaticamente o document do banco de dados e insere dados ficticios.

O projeto base está configurado para rodar na porta localhost:8080

Para executar as requisições, utilize Postman ou curl.

### Pré requisitos para execução local
```
Java Version "1.8.0_191"
Apache Maven 3.5.2
```
### Execução

Para executar o software execute o arquivo br.com.bots.BotsApplication ou 
rode o comando mvn install na pasta raiz do projeto depois execute o comando java -jar bots-0.0.1-SNAPSHOT.jar
que encontra-se no diretório /target

### Instalação

Para gerar um pacote e fazer upload em um servidor de aplicação faça o seguinte comando na pasta raiz do projeto

```
mvn install
```

### Tecnologias utilizadas

- Java 8
- Spring Boot 2.1.1.RELEASE
- Model Mapper 2.0.0
- Lombok 1.18.2
- Mockito 2.21.0
- Junit

### Banco de dados
- MongoDB (banco de dados orientado a documentos)

### Ambiente de desenvolvimento
- Ubuntu 18.04.1 LTS

### IDE
- Intellij IDEA 2018.3.2

### Itens a mais que poderia ter colocado por conta do tempo
- Testes
- Docker
- Swagger2

### Observações
Não é necessario a criação de banco de dados local, o banco encontra-se no MongoDB Atlas.

