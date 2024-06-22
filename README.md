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
git clone https://github.com/Gabriel-sy/PicPaySimplificado.git
```
Entre no diretório do projeto
```bash
cd PicPaySimplificado
```
Execute o projeto usando Maven
```bash
mvn clean install
mvnw spring-boot:run
```
A aplicação vai estar rodando na porta 8080.
## Endpoints

**GET USER**
```markdown
GET /{id} - Retorna o usuário com o id passado. A aplicação inicia com 4 usuários padrão, 
com os IDs 1, 2, 3, 4. 1 e 2 são lojistas e não podem fazer pagamentos,
3 e 4 são usuários comuns, podem pagar e receber.
```
```json
{
    "id": 1,
    "money": 100.00,
    "name": "exampleRetailer1",
    "document": "23453234388",
    "email": "example1@gmail.com",
    "userType": "RETAILER"
}
```

**GET HISTORY**
```markdown
GET /history - Retorna o histórico de transações
```
```json
[
  {
    "id": 1,
    "transferAmount": 20.00,
    "timestamp": "2024-06-22T10:52:22.389474",
    "payer": {
      "id": 3,
      "money": 80.00,
      "name": "exampleCommon1",
      "document": "23453234399",
      "email": "example3@gmail.com",
      "userType": "COMMON"
    },
    "payee": {
      "id": 1,
      "money": 120.00,
      "name": "exampleRetailer1",
      "document": "23453234388",
      "email": "example1@gmail.com",
      "userType": "RETAILER"
    }
  }
]
```

**POST TRANSFER**
```markdown
POST /transfer - Realiza transação entre dois usuários, lojistas só podem receber.
(A transação leva algumas tentativas até ser aprovada)
```
```json
{
    "value": 20,
    "payer": 3,
    "payee": 1
}
```
