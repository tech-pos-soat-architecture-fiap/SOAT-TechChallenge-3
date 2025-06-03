
# TechChallenge

Este projeto consiste no desenvolvimento de um sistema de autoatendimento para uma lanchonete em expansão. O objetivo é de ser um sistema para gerenciamento de pedidos, gerenciamento de produtos e clientes, e acompanhamento do status dos pedidos tanto na parte administrativa, quanto na parte do cliente.


## Principais funcionalidades

- Cadastro e identificação de clientes.
- Gerenciamento de produtos e categorias (Lanche, Acompanhamento, Bebida, Sobremesa).
- Busca de produtos por categoria.
- Realização e acompanhamento de pedidos.
- Checkout (envio dos produtos escolhidos para a fila de pedidos).
- Listagem de pedidos.



## Tecnologias Utilizadas


- Java 24: A linguagem base adotada para o desenvolvimento do projeto.
- Spring Framework: Framework para desenvolvimento de aplicações Java, oferecendo suporte a diversas funcionalidades.
- Spring Boot: Framework que simplifica a configuração e o desenvolvimento de aplicações Spring, permitindo uma inicialização rápida e fácil.
- Spring Data JPA: Biblioteca para integração com bancos de dados, facilitando o acesso e manipulação de dados.
- Swagger: Ferramenta para documentação de APIs REST, permitindo a visualização e testes das rotas disponíveis.
- Postgres: Banco de dados relacional utilizado para persistência de dados.
- Hibernate: ORM (Object-Relational Mapping) utilizado para mapear objetos Java para tabelas do banco de dados.
- Docker: Ferramenta para criação e gerenciamento de contêineres, permitindo a execução da aplicação em ambientes isolados.
- Docker Compose: Ferramenta para definir e executar aplicações multi-contêineres, facilitando a orquestração dos serviços necessários para o projeto.


## Pré-requisitos
- [Docker](https://docs.docker.com/engine/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/downloads)

## Executar projeto localmente

- Clonando o repositório
```bash
  # SSH
  git clone git@github.com:tech-pos-soat-architecture-fiap/SOAT-TechChallenge-1.git
  
  # HTTPS
  git clone https://github.com/tech-pos-soat-architecture-fiap/SOAT-TechChallenge-1.git
```

- Entrar na diretório do projeto

```bash
  cd SOAT-TechChallenge-1
```
- Subir a aplicação com o docker compose:

```bash
  docker compose --profile=prod up -d
```
> [!NOTE]
> Usamos a flag '--profile=prod' para subir a aplicação, sem isso somente as dependências, como o postgres, por exemplo, irão ser executadas. Com isso facilita o desenvolvimento local.


## Documentação
- [Documentação do sistema (DDD) com Event Storming](https://miro.com/app/board/uXjVI3-v7GA=/)
- [API Reference](docs/API-Reference.md)
- [Documentação API com Swagger](http://localhost:8000/swagger-ui/index.html) (aplicação precisar estar de pé)

## Observações
- O projeto não inclui frontend, apenas backend.
- Para dúvidas ou problemas, consulte os integrantes da [organização](https://github.com/tech-pos-soat-architecture-fiap).