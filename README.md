# Builders-Clients

## Resumo:
API REST que representa um MVP de cadastro de clientes, em que é possível criar, editar, listar, buscar e atualizar os dados de clientes por completo e também de forma granular. Para visualizar os registros não precisa estar logado, mas para criar, atualizar, buscar ou remover é preciso estar logado com autenticação JWT.

## Tecnologias Utilizadas:
-Java

-Spring Boot, Data, Security

-Token JWT

-Beans Validation

-Banco de Dados H2

-Maven

-Swagger

-Docker

## Mais informações:
-Documentação Swagger detalhada da API pode ser acessada através da requisição "/swagger-ui/"

-Para se logar, basta fazer um POST para "/auth" passando login e password

-Na pasta src/main/resources há um arquivo data.sql que contém os inserts de alguns registros que populam o BD.

-Usuário para testes:

login: alexandre
password: 123456

-Foi gerada uma imagem Docker, criado um container e hospedado no provedor Heroku, segue a URL:
https://buildersclient.herokuapp.com/
