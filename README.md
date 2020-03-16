## Instruções

### Pré requisitos

- Possuir o git
- Possuir o mongo ou um container do mongo na porta 27017
- Possuir o maven
- Possuir o docker

## Instalação

- O primeiro passo é a clonagem do projeto pelo link: https://github.com/donascimentomarcelo/api.git
- Após a clonagem, é necessário acessar a pasta do projeto e executar os comandos abaixo:
- mvn clean install
- docker-compose up

## Documentação

- Após executar a instalação, o projeto irá rodar num container na porta 8080, é importante que esta porta não esteja em uso
- O projeto foi documentado usando o Swagger, com isso basta acessar http://localhost:8080/swagger-ui.html#/ para ter acesso aos endpoints.