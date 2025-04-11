# Desafio Engenheiro de Software - BTG Pactual

## 📌 Sobre o Projeto
Este projeto foi desenvolvido como parte do **Desafio de Engenheiro de Software do BTG Pactual**, com o objetivo de processar pedidos recebidos via RabbitMQ e armazená-los em uma base de dados MongoDB. A aplicação também expõe uma API REST para consulta de relatórios sobre os pedidos processados.

## 🚀 Tecnologias Utilizadas
- **Java 21** + **Spring Boot** (API REST e microsserviço)
- **RabbitMQ** (Mensageria assíncrona)
- **MongoDB** (Banco de dados NoSQL para armazenamento dos pedidos)
- **Docker** (Containerização e orquestração)

## ⚙️ Funcionalidades
✅ Consumo de mensagens do RabbitMQ contendo pedidos 
✅ Cálculo do valor total dos pedidos
✅ Armazenamento eficiente dos pedidos no MongoDB
✅ API REST para consulta de:
   - Valor total do pedido
   - Quantidade de pedidos por cliente
   - Lista de pedidos realizados por cliente
✅ Documentação completa

## 📦 Arquitetura do Projeto
O sistema segue a arquitetura de microsserviços e está estruturado da seguinte forma:

```
📁 desafio-btg-pactual
│── 📂 local
│   │── 📄 docker-compose.yml  # Orquestração dos serviços (MongoDB, RabbitMQ e aplicação)
│── 📂 src
│   ├── 📂 main
│   │   ├── 📂 java/com/seuusuario
│   │   │   ├── 📂 controller      # Controladores da API REST
│   │   │   ├── 📂 service         # Lógica de negócios
│   │   │   ├── 📂 listener         # Consumir mensagens da fila do RabbitMQ
│   │   │   ├── 📂 repository      # Acesso ao banco de dados
│   │   │   ├── 📂 entity           # Modelos das entidades
│   │   │   ├── 📂 config          # Configurações da aplicação (RabbitMQ, MongoDB, etc.)
```

## 📊 Modelagem da Base de Dados
A base de dados MongoDB armazena os pedidos no seguinte formato:
```json
{
   "codigoPedido": 1001,
   "codigoCliente": 1,
   "itens": [
       {
           "produto": "lápis",
           "quantidade": 100,
           "preco": 1.10
       },
       {
           "produto": "caderno",
           "quantidade": 10,
           "preco": 1.00
       }
   ],
   "valorTotal": 111.00
}
```

## 🔧 Como Executar
### Pré-requisitos
- Docker e Docker Compose instalados
- Java 21 e Maven

### Passos
1. Clone este repositório:
   ```sh
   git clone https://github.com/seuusuario/desafio-btg-pactual.git
   cd desafio-btg-pactual
   ```
2. Execute a aplicação via Docker:
   ```sh
   docker-compose up --build
   ```
3. A API estará disponível em `http://localhost:8080`

## 🛠 Endpoints da API
| Método | Endpoint | Descrição |
|--------|---------|-----------|
| `GET` | `/customers/{codigoCliente}/orders` | Retorna todas as Informaçoes do Pedido de um usuario |


