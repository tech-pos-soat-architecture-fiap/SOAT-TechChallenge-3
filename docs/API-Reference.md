# API Reference

## Produto

<details>
<summary>
 Cadastro de produto
</summary>

**Método:** POST  
**URI:** `/products`


**Exemplo request:**
```bash
curl -X POST "http://localhost:8000/products" \
  -H "Content-Type: application/json" \
  -d '{"name":"Produto X","category":"Categoria Y","price":99.99,"description":"Descrição do produto","images":[{"url":"http://exemplo.com/img.jpg","description":"Imagem principal","position":1}]}'
```

Resposta: **200 OK**
```json
"/products/1"
```
</details>


<details>
<summary>
 Obter produto por ID
</summary>

**Método:** GET  
**URI:** `/products/{id}`

**Parâmetros:**

*   `id` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/products/1"
```

Resposta: **200 OK**
```json
{
  "name": "Hambúrguer",
  "category": "Lanche",
  "price": 0,
  "description": "Muito saboroso",
  "images": [
    {
      "url": "https://example.com/image.jpg",
      "description": "Imagem do produto",
      "position": 0
    }
  ]
}
```
</details>

<details>
<summary>
Atualizar produto
</summary>

**Método:** PUT  
**URI:** `/products/{id}`

**Parâmetros:**

*   `id` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X PUT "http://localhost:8000/products/1" \
  -H "Content-Type: application/json" \
  -d '{"name":"Produto X","category":"Categoria Y","price":99.99,"description":"Descrição do produto","images":[{"url":"http://exemplo.com/img.jpg","description":"Imagem principal","position":1}]}'
```

Resposta: **200 OK**
```json
"/products/1"
```
</details>

<details>
<summary>
Remover produto
</summary>

**Método:** DELETE  
**URI:** `/products/{id}`

**Parâmetros:**

*   `id` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X DELETE "http://localhost:8000/products/1"
```

Resposta: **200 OK**

(No content)

</details>

<details>
<summary>
Listar produtos
</summary>

**Método:** GET  
**URI:** `/products`

**Parâmetros:**

*   `page` (query, optional): `integer` (int32, default: 0)
*   `size` (query, optional): `integer` (int32, default: 10)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/products?page=0&size=10"
```


Resposta: **200 OK**

```json
{
  "totalElements": 0,
  "totalPages": 0,
  "currentPage": 0,
  "elementsPerPage": 0,
  "content": [
    {
      "name": "Hambúrguer",
      "category": "Lanche",
      "price": 0,
      "description": "Muito saboroso",
      "images": [
        {
          "url": "https://example.com/image.jpg",
          "description": "Imagem do produto",
          "position": 0
        }
      ]
    }
  ]
}
```

</details>

<details>
<summary>
Listar produtos por categoria
</summary>

**Método:** GET  
**URI:** `/products/category/{category}`

**Parâmetros:**
*   `category` (path, required): string
*   `page` (query, optional): `integer` (int32, default: 0)
*   `size` (query, optional): `integer` (int32, default: 10)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/products/category/lanche?page=0&size=10"
```


Resposta: **200 OK**

```json
{
  "totalElements": 0,
  "totalPages": 0,
  "currentPage": 0,
  "elementsPerPage": 0,
  "content": [
    {
      "name": "Hambúrguer",
      "category": "Lanche",
      "price": 0,
      "description": "Muito saboroso",
      "images": [
        {
          "url": "https://example.com/image.jpg",
          "description": "Imagem do produto",
          "position": 0
        }
      ]
    }
  ]
}
```

</details>

## Usuário

<details>
<summary>
 Cadastro de usuário
</summary>

**Método:** POST  
**URI:** `/users`


**Exemplo request:**
```bash
curl -X POST "http://localhost:8000/users" \
  -H "Content-Type: application/json" \
  -d '{"name":"João","email":"joao@email.com","cpf":"12345678900"}'
```

Resposta: **200 OK**
```json
"/users/1"
```
</details>

<details>
<summary>
Listar usuários
</summary>

**Método:** GET  
**URI:** `/users`

**Parâmetros:**

*   `page` (query, optional): `integer` (int32, default: 0)
*   `size` (query, optional): `integer` (int32, default: 10)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/users?page=0&size=10"
```

Resposta: **200 OK**

```json
{
  "totalElements": 0,
  "totalPages": 0,
  "currentPage": 0,
  "elementsPerPage": 0,
  "content": [
    {
      "id": 0,
      "name": "João",
      "email": "joão@gmail.com",
      "cpf": "000.000.000-00"
    }
  ]
}
```

</details>

<details>
<summary>
 Obter usuário por ID
</summary>

**Método:** GET  
**URI:** `/users/{id}`

**Parâmetros:**

*   `id` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/users/1"
```

Resposta: **200 OK**
```json
{
  "id": 0,
  "name": "João",
  "email": "joão@gmail.com",
  "cpf": "000.000.000-00"
}
```
</details>

<details>
<summary>
 Obter usuário por CPF
</summary>

**Método:** GET  
**URI:** `/users/cpf/{cpf}`

**Parâmetros:**

*   `cpf` (path, required): `string`

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/users/cpf/12345678900"
```

Resposta: **200 OK**
```json
{
  "id": 0,
  "name": "João",
  "email": "joão@gmail.com",
  "cpf": "000.000.000-00"
}
```
</details>

## Pedido

<details>
<summary>
 Criação de pedido
</summary>

**Método:** POST  
**URI:** `/orders`


**Exemplo request:**
```bash
curl -X POST "http://localhost:8000/orders" \
  -H "Content-Type: application/json" \
  -d '1'
```

Resposta: **201 Created**
```json
"/orders/1"
```
</details>

<details>
<summary>
 Obter pedido por ID
</summary>

**Método:** GET  
**URI:** `/orders/{orderId}`

**Parâmetros:**

*   `orderId` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/orders/1"
```

Resposta: **200 OK**
```json
{
  "id": 0,
  "createdAt": "2025-06-03T14:30:00.000Z",
  "items": [
    {
      "quantity": 0,
      "price": 0,
      "productName": "string"
    }
  ],
  "total": 0,
  "userId": 0
}
```
</details>

<details>
<summary>
Listar pedidos ativos
</summary>

**Método:** GET  
**URI:** `/orders/active`

**Parâmetros:**

*   `page` (query, optional): `integer` (int32, default: 0)
*   `size` (query, optional): `integer` (int32, default: 10)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/orders/active?page=0&size=10"
```


Resposta: **200 OK**

```json
{
  "totalElements": 0,
  "totalPages": 0,
  "currentPage": 0,
  "elementsPerPage": 0,
  "content": [
    {
      "id": 0,
      "createdAt": "2025-06-03T14:30:00.000Z",
      "items": [
        {
          "quantity": 0,
          "price": 0,
          "productName": "Hambúrguer"
        }
      ],
      "total": 0,
      "userId": 0
    }
  ]
}
```

</details>

<details>
<summary>
 Adicionar itens ao pedido
</summary>

**Método:** POST  
**URI:** `/orders/{orderId}/items`

**Parâmetros:**

*   `orderId` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X POST "http://localhost:8000/orders/1/items" \
  -H "Content-Type: application/json" \
  -d '[{"productId":2,"quantity":3}]'
```

Resposta: **200 OK**
```json
{
  "id": 0,
  "createdAt": "2025-06-03T14:30:00.000Z",
  "items": [
    {
      "quantity": 0,
      "price": 0,
      "productName": "Produto X"
    }
  ],
  "total": 0,
  "userId": 0
}
```
</details>

<details>
<summary>
 Remover itens de um pedido
</summary>

**Método:** DELETE  
**URI:** `/orders/{orderId}/items`

**Parâmetros:**

*   `orderId` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X DELETE "http://localhost:8000/orders/1/items" \
  -H "Content-Type: application/json" \
  -d '[{"productId":2,"quantity":1}]'
```

Resposta: **200 OK**
```json
{
  "id": 0,
  "createdAt": "2025-06-03T14:30:00.000Z",
  "items": [
    {
      "quantity": 0,
      "price": 0,
      "productName": "Produto X"
    }
  ],
  "total": 0,
  "userId": 0
}
```
</details>
<details>
<summary>
 Fazer checkout do pedido
</summary>

**Método:** POST  
**URI:** `/orders/{orderId}/payment`

**Parâmetros:**

* `orderId` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X POST "http://localhost:8000/orders/1/payment"
```
Resposta: **200 OK**

```json
{
  "storeOrderId": "95e2c402-34b4-4b86-838b-71859a0c70e0",
  "paymentId": 1,
  "orderId": 1,
  "qrcodeData": "00020101021243650016COM.MERCADOLIBRE02013063638c84c8c55-050c-4329-a097-526d63fe632b35204000053039865802BR5925IZABEL AAAA DE MELO6007BARUERI62070503***63040B6D1"
}
```

</details>

<details>
<summary>
 Alterar status do pedido
</summary>

**Método:** PUT  
**URI:** `/orders/{orderId}/status`

**Parâmetros:**

*   `orderId` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X PUT "http://localhost:8000/orders/1/status"
```

Resposta: **200 OK**
```json
{
  "id": 0,
  "statusName": "RECEIVED"
}
```
</details>

## Pagamento

<details>
<summary>
 Obter status do pagamento
</summary>

**Método:** GET  
**URI:** `/payments/{paymentId}/status`

**Parâmetros:**

*   `paymentId` (path, required): `integer` (int64)

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/payments/1/status"
```

Resposta: **200 OK**
```json
{
  "paymentId": 1,
  "status": "PENDING"
}
```
</details>

<details>
<summary>
 Confirmar pagamento (Webhook)
</summary>

**Método:** POST  
**URI:** `/payments/confirm`

**Exemplo request:**
```bash
curl -X POST "http://localhost:8000/payments/confirm" \
  -H "Content-Type: application/json" \
  -d '{"paymentId":1,"status":"CONFIRMED"}'
```

Resposta: **200 OK**

(No content)

</details>

## Sistema

<details>
<summary>
 Health Check
</summary>

**Método:** GET  
**URI:** `/actuator/health`

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/actuator/health"
```

Resposta: **200 OK**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP"
    }
  }
}
```
</details>

<details>
<summary>
 Informações da aplicação
</summary>

**Método:** GET  
**URI:** `/actuator/info`

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/actuator/info"
```

Resposta: **200 OK**
```json
{
  "app": {
    "name": "TechFood",
    "description": "Sistema de autoatendimento para lanchonete"
  }
}
```
</details>

<details>
<summary>
 Métricas da aplicação
</summary>

**Método:** GET  
**URI:** `/actuator/metrics`

**Exemplo request:**
```bash
curl -X GET "http://localhost:8000/actuator/metrics"
```

Resposta: **200 OK**
```json
{
  "names": [
    "jvm.memory.used",
    "jvm.memory.max",
    "process.cpu.usage"
  ]
}
```
</details>



