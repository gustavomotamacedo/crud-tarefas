# Documentação da API de Lista de Tarefas (Spring Boot)

API RESTful desenvolvida em Java com o framework Spring Boot, para gerenciamento de usuários e suas respectivas tarefas. A aplicação utiliza um banco de dados relacional com três tabelas principais: `Usuarios`, `Tarefas` e `Estado`. Abaixo estão descritos os endpoints disponíveis na API.

---

## **Endpoints**

### **1. Cadastro de Usuário**
- **URL**: `/api/usuarios`
- **Método**: `POST`
- **Descrição**: Cadastra um novo usuário no sistema.
- **Body**:
  ```json
  {
      "nome": "Alo",
      "email": "teste1@gmail.com",
      "senha": "123"
  }
  ```
- **Resposta**:
  - **Código**: `201 Created`
  - **Corpo**: Retorna o usuário cadastrado.
    ```json
    {
        "id": 1,
        "nome": "Alo",
        "email": "teste1@gmail.com"
    }
    ```

---

### **2. Login de Usuário**
- **URL**: `/api/usuarios/login`
- **Método**: `POST`
- **Descrição**: Realiza o login do usuário com base no email e senha fornecidos.
- **Body** (form-data):
  - `email`: `teste1@gmail.com`
  - `senha`: `123`
- **Resposta**:
  - **Código**: `200 OK`
  - **Corpo**: Retorna um token JWT ou mensagem de sucesso.
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

---

### **3. Listar Tarefas de um Usuário**
- **URL**: `/api/tarefas`
- **Método**: `GET`
- **Descrição**: Retorna a lista de tarefas associadas ao usuário autenticado.
- **Autenticação**: Requer token JWT no cabeçalho `Authorization`.
- **Resposta**:
  - **Código**: `200 OK`
  - **Corpo**: Lista de tarefas com seus estados.
    ```json
    [
        {
            "id": 1,
            "descricao": "Estudar Spring Boot",
            "estado": {
                "id": 1,
                "nome": "a fazer"
            }
        },
        {
            "id": 2,
            "descricao": "Fazer exercícios",
            "estado": {
                "id": 2,
                "nome": "feita"
            }
        }
    ]
    ```

---

### **4. Cadastrar uma Nova Tarefa**
- **URL**: `/api/tarefas`
- **Método**: `POST`
- **Descrição**: Cadastra uma nova tarefa para o usuário autenticado.
- **Autenticação**: Requer token JWT no cabeçalho `Authorization`.
- **Body**:
  ```json
  {
      "descricao": "Estudar Spring Boot"
  }
  ```
- **Resposta**:
  - **Código**: `201 Created`
  - **Corpo**: Retorna a tarefa cadastrada.
    ```json
    {
        "id": 3,
        "descricao": "Estudar Spring Boot",
        "estado": {
            "id": 1,
            "nome": "a fazer"
        }
    }
    ```

---

### **5. Deletar uma Tarefa por ID**
- **URL**: `/api/tarefas/{id}`
- **Método**: `DELETE`
- **Descrição**: Remove uma tarefa específica pelo seu ID.
- **Autenticação**: Requer token JWT no cabeçalho `Authorization`.
- **Parâmetros**:
  - `id` (path): ID da tarefa a ser deletada.
- **Resposta**:
  - **Código**: `204 No Content`
  - **Corpo**: Nenhum conteúdo retornado.

---

### **6. Atualizar uma Tarefa por ID**
- **URL**: `/api/tarefas/{id}`
- **Método**: `PUT`
- **Descrição**: Atualiza a descrição de uma tarefa específica pelo seu ID.
- **Autenticação**: Requer token JWT no cabeçalho `Authorization`.
- **Parâmetros**:
  - `id` (path): ID da tarefa a ser atualizada.
- **Body**:
  ```json
  {
      "descricao": "Estudar mais sobre APIs REST"
  }
  ```
- **Resposta**:
  - **Código**: `200 OK`
  - **Corpo**: Retorna a tarefa atualizada.
    ```json
    {
        "id": 1,
        "descricao": "Estudar mais sobre APIs REST",
        "estado": {
            "id": 1,
            "nome": "a fazer"
        }
    }
    ```

---

## **Banco de Dados**

O banco de dados possui três tabelas principais:

### **1. Tabela `Usuarios`**
- **Campos**:
  - `id`: Identificador único do usuário.
  - `nome`: Nome do usuário.
  - `email`: Email do usuário (único).
  - `senha`: Senha criptografada do usuário.

### **2. Tabela `Tarefas`**
- **Campos**:
  - `id`: Identificador único da tarefa.
  - `descricao`: Descrição da tarefa.
  - `usuario_id`: Chave estrangeira referenciando o ID do usuário que criou a tarefa.
  - `estado_id`: Chave estrangeira referenciando o ID do estado da tarefa.

### **3. Tabela `Estado`**
- **Campos**:
  - `id`: Identificador único do estado.
  - `nome`: Nome do estado (`"a fazer"` ou `"feita"`).

---

## **Tecnologias Utilizadas**
- **Framework**: Spring Boot 
- **Banco de Dados**: Relacional (ex.: MySQL, PostgreSQL)
- **Autenticação**: JWT (JSON Web Token) 
- **Documentação**: Pode ser integrada com Swagger ou Apidog para facilitar o consumo da API .

---

## **Exemplo de Uso**

### **Cadastrar um Usuário**
```bash
curl -X POST http://localhost:8080/api/usuarios \
-H "Content-Type: application/json" \
-d '{
    "nome": "Alo",
    "email": "teste1@gmail.com",
    "senha": "123"
}'
```

### **Listar Tarefas de um Usuário**
```bash
curl -X GET http://localhost:8080/api/tarefas \
-H "Authorization: Bearer <TOKEN>"
```