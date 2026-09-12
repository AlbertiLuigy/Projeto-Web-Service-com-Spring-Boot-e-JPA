# Web Services com Spring Boot, JPA / Hibernate

<div align="center">

![Java](https://img.shields.io/badge/java-21-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![H2](https://img.shields.io/badge/H2-Database-0078d4?style=for-the-badge)

</div>

## 📌 Sobre o projeto

Este projeto implementa uma **API RESTful** para gerenciamento de pedidos (commerce), construída com **Spring Boot** e **Spring Data JPA**. Foi desenvolvido como parte de um curso prático de Java, aplicando os conceitos fundamentais de Arquitetura em Camadas, Mapeamento Objeto-Relacional (ORM) e Tratamento de Exceções Customizadas.

O sistema gerencia **Usuários**, **Pedidos**, **Itens de Pedido**, **Produtos**, **Categorias** e **Pagamentos**, incluindo todos os relacionamentos entre essas entidades (Muitos-para-Um, Um-Para-Muitos, Muitos-Para-Muitos com tabela de junção e Um-Para-Um com chave primária compartilhada).

---

## ✅ Funcionalidades implementadas

### Usuários (`/users`)
| Método HTTP | Endpoint      | Descrição                                   | Tratamento de erro       |
| :---------- | :------------ | :------------------------------------------ | :----------------------- |
| `GET`       | `/users`      | Lista todos os usuários cadastrados         | —                        |
| `GET`       | `/users/{id}` | Busca um usuário por ID                     | `404 Not Found`          |
| `POST`      | `/users`      | Cadastra um novo usuário                    | `201 Created`            |
| `PUT`       | `/users/{id}` | Atualiza os dados de um usuário existente  | `404 Not Found`          |
| `DELETE`    | `/users/{id}` | Remove um usuário                           | `404 Not Found` + `400 Bad Request` (integridade) |

### Categorias (`/categories`)
| Método HTTP | Endpoint           | Descrição                 | Tratamento de erro       |
| :---------- | :----------------- | :------------------------ | :----------------------- |
| `GET`       | `/categories`      | Lista todas as categorias | —                        |
| `GET`       | `/categories/{id}` | Busca categoria por ID    | `404 Not Found`          |

### Produtos (`/products`)
| Método HTTP | Endpoint          | Descrição                | Tratamento de erro       |
| :---------- | :---------------- | :----------------------- | :----------------------- |
| `GET`       | `/products`       | Lista todos os produtos  | —                        |
| `GET`       | `/products/{id}`  | Busca produto por ID     | `404 Not Found`          |

### Pedidos (`/orders`)
| Método HTTP | Endpoint        | Descrição                                            | Tratamento de erro       |
| :---------- | :-------------- | :--------------------------------------------------- | :----------------------- |
| `GET`       | `/orders`       | Lista todos os pedidos                               | —                        |
| `GET`       | `/orders/{id}`  | Busca pedido por ID (com itens, total e pagamento)  | `404 Not Found`          |

---

## 🏗️ Arquitetura em Camadas

```
com.projetoeducandoweb.curso
│
├── config/                  → Configurações do perfil 'teste' (DB Seeding via CommandLineRunner)
│    └── TesteConfig.java
│
├── entities/                → Modelo de domínio (Entidades JPA)
│    ├── enums/              → Enum OrderStatus
│    ├── pk/                 → Chave composta (OrderItemPk)
│    ├── User.java
│    ├── Order.java
│    ├── OrderItem.java
│    ├── Product.java
│    ├── Category.java
│    └── Payment.java
│
├── repositories/            → Interfaces Spring Data JPA (DAO / Acesso a Dados)
│    ├── UserRepository
│    ├── OrderRepository
│    ├── OrderItemRepository
│    ├── ProductRepository
│    └── CategoryRepository
│
├── service/                 → Camada de negócio (Services + Exceções)
│    ├── exceptions/
│    │    ├── ResourceNotFoundException.java
│    │    └── DatabaseExceptions.java
│    ├── UserService.java   (CRUD completo + tratamento de erros)
│    ├── CategoryService.java
│    ├── ProductService.java
│    └── OrderService.java
│
├── resources/               → Camada de apresentação (REST Controllers + Tratamento global de erros)
│    ├── exceptions/
│    │    ├── ResourceExceptionHandler.java   (@ControllerAdvice)
│    │    └── StandardError.java              (Corpo padrão de erro JSON)
│    ├── UserResources.java
│    ├── CategoryResources.java
│    ├── ProductResources.java
│    └── OrderResources.java
│
└── CursoApplication.java    → Ponto de entrada
```

### Design Pattern aplicado
- **Inversão de Controle / Injeção de Dependência** (`@Autowired`, `@Service`, `@RestController`) — baixo acoplamento
- **Repository Pattern** via Spring Data JPA — abstrai as operações de CRUD do banco
- **Global Exception Handler** via `@ControllerAdvice` — centraliza o tratamento de exceções e evita blocos try/catch nos controllers

---

## 🗄️ Modelo Relacional de Dados

```
┌──────────────┐         ┌──────────────────┐         ┌──────────────┐
│   tb_user    │1───────∞│    tb_order      │1───────1│  tb_payment  │
│──────────────│         │──────────────────│         │──────────────│
│  id (PK)     │         │  id (PK)         │         │  id (PK,FK)  │
│  name        │         │  moment          │         │  moment      │
│  email       │         │  orderStatus     │         │  order_id FK │
│  phone       │         │  client_id (FK)  │         └──────────────┘
│  password    │         └──────────────────┘
└──────────────┘                   ∞
                                    │
                                    │ (tabela de junção)
                                    ∞
                           ┌──────────────────────┐         ┌────────────────┐         ┌──────────────┐
                           │    tb_order_item     │∞───────1│   tb_product   │∞───────∞│  tb_category │
                           │──────────────────────│         │────────────────│         │──────────────│
                           │  order_id (PK,FK)    │         │  id (PK)       │         │  id (PK)     │
                           │  product_id (PK,FK)  │         │  name          │─────┐   │  name        │
                           │  quantity            │         │  description   │     │   └──────────────┘
                           │  price               │         │  price         │     │
                           └──────────────────────┘         │  imgUrl        │     │   ┌──────────────────────┐
                                                             └────────────────┘     └──∞│ tb_product_category  │
                                                                                        │──────────────────────│
                                                                                        │ product_id (PK,FK)  │
                                                                                        │ category_id (PK,FK) │
                                                                                        └──────────────────────┘
```

### Mapeamentos JPA usados
- `@ManyToOne` + `@JoinColumn` → pedido → cliente
- `@OneToMany(mappedBy)` → cliente → pedidos; pedido → itens
- `@ManyToMany` + `@JoinTable` → produto ↔ categoria (tabela `tb_product_category`)
- `@OneToOne(cascade = ALL)` + `@MapsId` → pedido ↔ pagamento (pk compartilhada)
- `@EmbeddedId` → chave composta em `OrderItem`

---

## 🛠️ Stack tecnológico

| Camada               | Tecnologia                                    | Versão       |
| :------------------- | :-------------------------------------------- | :----------- |
| Linguagem            | **Java SE** (LTS)                             | **21**       |
| Framework principal  | **Spring Boot**                               | **4.1.1**    |
| Web / REST           | **Spring Boot Starter Webmvc** (Tomcat embed) | incluso      |
| Persistência ORM     | **Spring Data JPA + Hibernate**               | incluso      |
| Banco de Testes      | **H2 Database** (em memória)                  | runtime      |
| Banco de Produção    | **PostgreSQL** (driver incluso, não usado no perfil teste) | runtime |
| Build                | **Apache Maven Wrapper**                      | incluso      |
| Console H2           | `spring-boot-h2console` (módulo dedicado SB4) | incluso      |

---

## 🚀 Como executar o projeto

### Pré-requisitos
- Java 21+ instalado (verifique com `java -version`)
- Nenhuma instalação de banco de dados é necessária (usamos H2 em memória)

### Passo a passo

```bash
# 1. Clonar o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git

# 2. Entrar na pasta do projeto Spring Boot
cd seu-repositorio/curso

# 3. Executar a aplicação (Windows PowerShell)
.\mvnw.cmd spring-boot:run
```

Ao iniciar, o Spring Boot automaticamente:
1. Cria as tabelas no H2 (`spring.jpa.hibernate.ddl-auto=create`)
2. Popula o banco com dados de seed (via `TesteConfig`)
3. Sobe o Tomcat na porta **8080**

---

## 🧪 Como testar os endpoints

### Console de administração do H2
Abra no navegador → **http://localhost:8080/h2-console**

Configuração de conexão (igual ao `application-teste.properties`):
| Campo       | Valor                          |
| :---------- | :----------------------------- |
| Driver Class| `org.h2.Driver`                |
| JDBC URL    | `jdbc:h2:mem:testdb`           |
| User Name   | `sa`                           |
| Password    | *(deixe vazio)*                |

### Endpoints da API (Postman / navegador)
```http
GET    http://localhost:8080/users
GET    http://localhost:8080/users/1
POST   http://localhost:8080/users          body: {"name":"Alex","email":"a@a.com","phone":"9999","password":"123"}
PUT    http://localhost:8080/users/1        body: {"name":"Maria 2","email":"m2@m.com","phone":"91111","password":"xx"}
DELETE http://localhost:8080/users/2

GET    http://localhost:8080/orders
GET    http://localhost:8080/orders/1

GET    http://localhost:8080/products
GET    http://localhost:8080/products/1

GET    http://localhost:8080/categories
GET    http://localhost:8080/categories/1
```

### Exemplos de tratamento de erros
Busca de usuário inexistente:
```http
GET /users/999  →  HTTP 404
```
```json
{
  "timestamp": "2026-09-12T12:00:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Resource not found. Id 999",
  "path": "/users/999"
}
```

---

## 🔍 Estado atual do projeto e pontos de melhoria

### ✅ O que está concluído e funcionando
- [x] Todas as 6 entidades modeladas com relacionamentos JPA corretos (incluindo @EmbeddedId em OrderItem e @MapsId em Payment)
- [x] 5 Repositories Spring Data JPA funcionando
- [x] Perfil `teste` (`application-teste.properties`) + `TesteConfig` populando o H2 com seed de produtos, categorias, usuários, pedidos, itens e pagamentos
- [x] Console H2 habilitado e acessível em `/h2-console`
- [x] **User CRUD completo** (findAll, findById, insert, update, delete) com tratamento de exceção 404/400
- [x] **Tratamento global de exceções** (`ResourceExceptionHandler` com `@ControllerAdvice`) + payload `StandardError` em JSON
- [x] **Tratamento 404 uniforme em TODOS os findById**: UserService, CategoryService, ProductService e OrderService agora usam `.orElseThrow(ResourceNotFoundException)` (nunca mais erro 500 por ID inexistente)
- [x] **User.update() corrigido**: usa `findById().orElseThrow()` ao invés de `getReferenceById()` com proxy lazy (evita erro 500 por exceção lançada fora do try/catch)
- [x] Order, Category, Product com operações de leitura (GET findAll/findById) e tratamento de erro 404
- [x] Cálculo automático do total do pedido (método `Order.getTotal()`)
- [x] Subtotal do item (método `OrderItem.getSubTotal()`)
- [x] Enum `OrderStatus` (corrigido de OrderStaus) com code int + método `valueOf(int)` customizado e proteção contra null
- [x] Correção de nomenclatura: `Product.name` (antes `nome` em pt-BR), `Order.orderStatus` (antes `orderStaus`), variável `productService` (antes `productServiceService`)
- [x] Correção de tipagem: entidades com atributos `Long` (wrapper) ao invés de `long` (primitivo) nos IDs, aceitando null no construtor (padrão JPA com auto-incremento)

### 🚧 O que pode ser implementado como próximos passos (não obrigatórios para o curso)
- [ ] **CRUD completo** para `Category`, `Product`, `Order` (hoje só têm GET - endpoints create/update/delete)
- [ ] **Tratamento de exceção 400** no POST/PUT de Category/Product/Order quando o payload for inválido
- [ ] **Validação de campos** via Bean Validation (`jakarta.validation`) — ex: @NotBlank no nome/email, @Email no email, @DecimalMin no preço
- [ ] **DTO (Data Transfer Object)** para requests/responses — evitar expor entidades JPA diretamente na API (ex: ocultar password do User)
- [ ] **Soft delete** no User (campo `deleted` booleano) ao invés de DELETE físico
- [ ] **Paginação** dos endpoints `findAll()` (`Pageable` do Spring Data)
- [ ] **Perfil `prod`** com PostgreSQL (já tem o driver no pom, faltaria só criar `application-prod.properties`)
- [ ] **Testes unitários** (JUnit 5 + Mockito) para a camada Service
- [ ] **Testes de integração** com @SpringBootTest nos endpoints REST
- [ ] **Spring Security** para autenticação/autorização (JWT) e criptografia de senha (BCrypt)
- [ ] Documentação da API com **SpringDoc OpenAPI / Swagger** (`/swagger-ui.html`)

---

## 📚 Conceitos Java/Spring consolidados neste projeto

1. Arquitetura em 3 camadas (Resources / Services / Repositories)
2. Injeção de Dependência e Inversão de Controle (`@Autowired`, `@Service`, `@RestController`)
3. Mapeamento Objeto-Relacional (JPA): `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
4. Mapeamento de associações: `@ManyToOne`, `@OneToMany`, `@ManyToMany`, `@OneToOne`, `@EmbeddedId`
5. Escolha de coleções: `List` vs `Set` (unicidade em produto↔categoria)
6. Spring Profiles (`application.properties` + `application-{profile}.properties`)
7. Database seeding com `CommandLineRunner`
8. Exceções customizadas herdando de `RuntimeException`
9. Global Exception Handler com `@ControllerAdvice` + `@ExceptionHandler`
10. Formatação JSON (`@JsonFormat` ISO 8601) e enumeração tipada com código
