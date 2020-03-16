## Instru��es

### Pr� requisitos

- Possuir o git
- Possuir o mongo ou um container do mongo na porta 27017
- Possuir o maven
- Possuir o docker

## Instala��o

- O primeiro passo � a clonagem do projeto pelo link: https://github.com/donascimentomarcelo/api.git
- Ap�s a clonagem, � necess�rio acessar a pasta do projeto e executar os comandos abaixo:
- mvn clean install
- docker-compose up

## Documenta��o

- Ap�s executar a instala��o, o projeto ir� rodar num container na porta 8080, � importante que esta porta n�o esteja em uso
- O projeto foi documentado usando o Swagger, com isso basta acessar http://localhost:8080/swagger-ui.html#/ para ter acesso aos endpoints.