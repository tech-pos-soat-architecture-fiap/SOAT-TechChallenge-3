
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
- Kubernetes: Plataforma de orquestração de contêineres para deploy e gerenciamento da aplicação em produção.
- Minikube: Ferramenta para executar Kubernetes localmente para desenvolvimento e testes.


## Pré-requisitos

### Para desenvolvimento local (Docker Compose)
- [Docker](https://docs.docker.com/engine/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/downloads)

### Para deploy em Kubernetes
- [Docker](https://docs.docker.com/engine/install/)
- [Minikube v1.36+](https://minikube.sigs.k8s.io/docs/start/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Git](https://git-scm.com/downloads)

## Executar projeto

### Desenvolvimento Local (Docker Compose)

- Clonando o repositório
```bash
  # SSH
  git clone git@github.com:tech-pos-soat-architecture-fiap/SOAT-TechChallenge-2.git
```
```bash
  # HTTPS
  git clone https://github.com/tech-pos-soat-architecture-fiap/SOAT-TechChallenge-2.git
```

- Entrar no diretório do projeto

```bash
  cd SOAT-TechChallenge-2
```
- Subir a aplicação com o docker compose:

```bash
  docker compose --profile=prod up -d
```
> [!NOTE]
> Usamos a flag '--profile=prod' para subir a aplicação, sem isso somente as dependências, como o postgres, por exemplo, irão ser executadas. Com isso facilita o desenvolvimento local.

### Deploy em Kubernetes (Minikube)

1. **Iniciar o Minikube:**
```bash
  minikube start
```

2. **Executar o script de deploy:**
```bash
  cd k8s/v1
  chmod +x tech-food-cli.sh
  ./tech-food-cli.sh
```

O script irá:
- Criar a imagem Docker da aplicação
- Carregar a imagem no Minikube
- Aplicar todos os manifestos Kubernetes (Deployment, Service, ConfigMap, Secret, HPA)
- Aguardar o deploy dos pods
- Exibir as URLs de acesso

3. **Acessar a aplicação:**
- Swagger UI: `http://<minikube-ip>:30000/swagger-ui/index.html`
- Health Check: `http://<minikube-ip>:30000/actuator/health`

### Comandos úteis para Kubernetes

```bash
# Verificar status dos pods
kubectl get pods

# Ver logs da aplicação
kubectl logs -f deployment/tech-food-deployment-v1

# Verificar HPA (Horizontal Pod Autoscaler)
kubectl get hpa

# Verificar serviços
kubectl get services

# Acessar o Minikube
minikube ip

# Parar o Minikube
minikube stop
```

## Documentação
- [Documentação do sistema (DDD) com Event Storming](https://miro.com/app/board/uXjVI3-v7GA=/)
- [API Reference](docs/API-Reference.md)
- [Documentação API com Swagger](http://localhost:8000/swagger-ui/index.html) (Docker Compose) ou `http://<minikube-ip>:30000/swagger-ui/index.html` (Kubernetes)

## Observações
- O projeto não inclui frontend, apenas backend.
- Para desenvolvimento local, use Docker Compose.
- Para testes de produção e escalabilidade, use Kubernetes com Minikube.
- Para dúvidas ou problemas, consulte os integrantes da [organização](https://github.com/tech-pos-soat-architecture-fiap).
