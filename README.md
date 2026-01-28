# Status do Projeto: Em andamento

Este projeto encontra-se em desenvolvimento.  
Atualmente, a API possui a estrutura base de autenticação, usuários e segurança. As funcionalidades relacionadas à entidade **Evento** ainda estão em fase de implementação.

---

# Eventos API

API REST desenvolvida com Spring Boot para um sistema de gerenciamento de eventos, utilizando autenticação e autorização via JWT, com separação de permissões entre usuários comuns e administradores.

O projeto tem como objetivo simular um cenário real de backend, priorizando boas práticas de segurança, organização do código e controle de acesso.

---

## Objetivo

Construir uma API onde:

- Usuários possam visualizar eventos e salvá-los como favoritos
- Administradores possam gerenciar eventos (criar, atualizar e excluir)
- O acesso aos recursos seja controlado por meio de roles (`USER` e `ADMIN`)

---

## Funcionalidades Implementadas

- Cadastro de usuários
- Autenticação com JWT
- Autorização baseada em roles
- Proteção de endpoints com Spring Security
- Estrutura base para testes de integração

---

## Funcionalidades em Desenvolvimento

- Entidade Evento
- Controller de eventos
- Endpoints para listagem de eventos
- Funcionalidade de favoritar/salvar eventos
- Endpoints administrativos para criação, atualização e exclusão de eventos
- Expansão das regras de negócio

---

## Configuração da Aplicação

Os arquivos `application.properties` e `application-test.properties` não contêm dados sensíveis diretamente.

As configurações de banco de dados e segurança são realizadas por meio de variáveis de ambiente, garantindo maior segurança e flexibilidade entre ambientes.

Exemplo de configuração utilizada nos arquivos `.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}
```
---

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (jjwt)
- MySQL
- H2 Database
- Maven
- JUnit

