# PicPay Simplificado

API desenvolvida em Spring Boot como parte de um [desafio](https://github.com/PicPay/picpay-desafio-backend). O objetivo era simular transações bancárias de um sistema de pagamentos.

## Funcionalidades

- Transferências entre contas
- Histórico de transações

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- H2 Database

## Instalação

### Requisitos
- JDK 17+
- Maven

### Iniciando aplicação

Clone o repositório
```bash
git clone https://github.com/Gabriel-sy/CRUD-spring-JWT.git
```
Navegue até o diretório do projeto
```bash
cd PicPaySimplificado
```
Execute o projeto usando Maven
```bash
./mvnw spring-boot:run
```

## Endpoints

- **POST** `/transfer` - Realiza uma transferência entre contas de usuários

### Histórico de Transações

- **GET** `/transactions/{userId}` - Obtém o histórico de transações de um usuário


