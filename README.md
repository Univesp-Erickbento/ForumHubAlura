# ForumHub API

## Descrição

A **ForumHub API** é uma aplicação RESTful projetada para gerenciamento de tópicos e usuários em um fórum online. A API utiliza Spring Boot para construção e exposição de endpoints e inclui funcionalidades de autenticação com JWT para segurança.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
  - Spring Security
  - Spring Data JPA
  - Spring Web
- **H2 Database**
- **Flyway** para migrações de banco de dados
- **JWT (JSON Web Token)** para autenticação
- **Swagger/OpenAPI** para documentação

---

## Endpoints Principais

### Autenticação

#### Login
**POST** `/auth/login`

Autentica um usuário e retorna um token JWT.

**Payload:**
```json
{
  "username": "user",
  "password": "password"
}
```

**Resposta:**
```json
"<jwt_token>"
```

### Gerenciamento de Tópicos

#### Listar Tópicos
**GET** `/api/topics`

Retorna uma lista paginada de tópicos.

**Parâmetros de Query:**
- `page` (opcional)
- `size` (opcional)

**Resposta:**
```json
[
  {
    "id": 1,
    "title": "Tópico de exemplo",
    "content": "Conteúdo do tópico",
    "author": "user"
  }
]
```

#### Obter Detalhes de um Tópico
**GET** `/api/topics/{id}`

Retorna os detalhes de um tópico pelo ID.

#### Criar Tópico
**POST** `/api/topics`

Cria um novo tópico.

**Payload:**
```json
{
  "title": "Novo tópico",
  "content": "Conteúdo do tópico"
}
```

#### Atualizar Tópico
**PUT** `/api/topics/{id}`

Atualiza um tópico existente.

**Payload:**
```json
{
  "title": "Tópico atualizado",
  "content": "Novo conteúdo do tópico"
}
```

#### Deletar Tópico
**DELETE** `/api/topics/{id}`

Remove um tópico do sistema.

---

## Configurações

### Banco de Dados

A aplicação utiliza o banco de dados H2 em memória para fins de desenvolvimento e testes.

**Propriedades:**
```properties
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Segurança

- O token JWT é configurado com uma chave secreta definida no arquivo de propriedades:
  ```properties
  api.security.token.secret=${JWT_SECRET:12345678}
  ```
- Login e senha para autenticação de usuários de exemplo:
  - **Usuário:** `user`
  - **Senha:** `password`

### Flyway

Flyway é usado para migração de banco de dados. As migrações devem ser colocadas no diretório `classpath:db/migration`.

---

## Documentação e Testes

### Swagger/OpenAPI
A documentação da API está disponível no endpoint:
```
/swagger-ui.html
```

### Console H2
Para acessar o console do banco de dados H2:
```
/h2-console
```

---

## Como Executar a API

1. Clone este repositório.
2. Configure o Java 17 no seu ambiente.
3. Compile e execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse o Swagger para testar os endpoints:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Melhorias Futuras

- Implementar roles adicionais para admin.
- Adicionar suporte para bancos de dados relacionais em produção (PostgreSQL/MySQL).
- Criar serviços para gerenciamento de usuários.

