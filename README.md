# TesteSeplag - Projeto para Processo Seletivo SeplagMT

Bem-vindo ao **TesteSeplag**, um projeto desenvolvido como parte do processo seletivo da SeplagMT. Esta aplicação é uma API RESTful construída com **Spring Boot**, que gerencia informações de pessoas, servidores e fotos, utilizando um banco de dados **PostgreSQL** e o **MinIO** para armazenamento de objetos (fotos). A autenticação é feita com **JWT**, e a documentação da API está disponível via **Swagger**.  

### Dados de Inscrição
Processo Seletivo: PSS 02/2025/SEPLAG (Analista de TI, Perfil Junior, Pleno e Sênior)  
Inscrição 1 DESENVOLVEDOR JAVA (BACK-END) - Sênior: 9133  
Inscrição 2 DESENVOLVEDOR JAVA (BACK-END) - Plano: 9135  
Inscrição 3 DESENVOLVEDOR FULL STACK - Sênior: 9138  
Nome: Diego Fernando Batista Silva  

## 📋 Visão Geral

O projeto é uma aplicação backend que permite:
- Gerenciar informações de pessoas e servidores (efetivos e temporários).
- Fazer upload e download de fotos associadas a pessoas, armazenadas no MinIO.
- Gerar URLs temporárias para acesso às fotos.
- Autenticar usuários com tokens JWT.

### Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.4.3**
- **PostgreSQL 16.8** (banco de dados relacional)
- **MinIO** (armazenamento de objetos)
- **Docker** e **Docker Compose** (para containerização)
- **Swagger/OpenAPI** (documentação da API)
- **Hibernate/JPA** (persistência de dados)
- **JWT** (autenticação e autorização)

### Estrutura do Projeto
- **Backend**: API RESTful construída com Spring Boot.
- **Banco de Dados**: PostgreSQL para dados relacionais.
- **Armazenamento de Arquivos**: MinIO para armazenar fotos.
- **Containerização**: Docker Compose para rodar a aplicação, o banco de dados e o MinIO.

## 🚀 Como Rodar a Aplicação

Siga os passos abaixo para rodar a aplicação localmente usando Docker Compose.

### Pré-requisitos
- **Docker** e **Docker Compose** instalados na sua máquina.
    - [Instalar Docker](https://docs.docker.com/get-docker/)
    - [Instalar Docker Compose](https://docs.docker.com/compose/install/)
- **Java 17** (necessário apenas se você for compilar o projeto manualmente).
- **Maven** (necessário apenas se você for compilar o projeto manualmente).
- **Git** (para clonar o repositório).

### Passo 1: Clonar o Repositório
Clone o repositório do GitHub para sua máquina local:

```bash
git clone https://github.com/fogeid/testeSeplag.git
cd testeSeplag
```

### Passo 2: Configurar o Ambiente
O projeto usa um arquivo `application.properties` para configurações. Certifique-se de que o arquivo `src/main/resources/application.properties` contém as seguintes configurações:

#### Configurações do Banco de Dados (PostgreSQL)
```bash
#### Configurações do PostgreSQL
spring.datasource.url=jdbc:postgresql://db:5432/postgres
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

#### Configurações do MinIO
minio.enabled=true
minio.url=http://minio:9000
minio.access-key=minioadmin
minio.secret-key=minioadmin
minio.bucket=fotos-pessoas

#### Configurações do JWT
jwt.secret=your-secret-key
```
- **Nota**: O `spring.jpa.hibernate.ddl-auto=create` recria o banco de dados a cada inicialização. Para produção, considere usar `update` ou um sistema de migrações como Flyway.

### Passo 3: Rodar a Aplicação com Docker Compose
O projeto inclui um arquivo `docker-compose.yml` que configura três serviços:
- `app`: A aplicação Spring Boot.
- `db`: O banco de dados PostgreSQL.
- `minio`: O servidor MinIO para armazenamento de objetos.

1. **Construa e inicie os contêineres**:
   No diretório raiz do projeto, execute:
docker-compose up -d --build

- O parâmetro `--build` garante que a imagem da aplicação seja recompilada.
- O parâmetro `-d` roda os contêineres em segundo plano.

2. **Verifique os logs**:
   Para confirmar que a aplicação iniciou corretamente, veja os logs do contêiner da aplicação:
   docker logs testeseplag-app-1

### Passo 4: Acessar a Documentação da API
A API está documentada com Swagger. Acesse a interface do Swagger para explorar os endpoints:

- **URL**: `http://localhost:8080/swagger-ui/index.html`

### Passo 5: Autenticar-se e Testar os Endpoints
A aplicação usa autenticação baseada em JWT. Siga os passos abaixo para obter um token e testar os endpoints.

1. **Obter um Token JWT**:
   Faça uma requisição POST para o endpoint de login:
```bash   
POST http://localhost:8080/api/auth/login
   Content-Type: application/json

{
  "username": "user",
  "password": "user123"
}
```

- **Resposta Esperada**:
```bash
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

- Anote o token retornado.

> **Nota**: O usuário `user` (senha: `user123`) e o usuário `admin` (senha: `admin123`) são criados automaticamente pelo `DataInitializer`.

2. **Testar um Endpoint Protegido**:
   Use o token para acessar um endpoint protegido, como o de geração de URL temporária para uma foto:
```bash
GET http://localhost:8080/fotos-pessoa/1/36/url
Authorization: Bearer <seu-token>
```

3. **Acessar a Foto**:
   Cole a URL temporária no navegador para visualizar a foto. Note que a URL expira em 5 minutos (300 segundos).

### Passo 6: Acessar o MinIO
Você pode acessar a interface web do MinIO para visualizar os arquivos armazenados:

- **URL**: `http://localhost:9000`
- **Usuário**: `minioadmin`
- **Senha**: `minioadmin`

Navegue até o bucket `fotos-pessoas` para ver as fotos enviadas.

## 🛠️ Endpoints Principais

Aqui estão alguns dos principais endpoints disponíveis na API:

| Método | Endpoint                          | Descrição                              | Autenticação Necessária |
|--------|-----------------------------------|----------------------------------------|-------------------------|
| POST   | `/api/auth/login`               | Autentica um usuário e retorna um JWT  | Não                     |
| GET    | `/pessoas`                      | Lista todas as pessoas                 | Sim (ROLE_USER)         |
| POST   | `/fotos-pessoa/upload/{pesId}`  | Faz upload de uma foto para uma pessoa | Sim (ROLE_USER)         |
| GET    | `/fotos-pessoa/{pesId}/{fpId}/url` | Gera uma URL temporária para uma foto | Sim (ROLE_USER)         |

- Para mais detalhes, consulte a documentação no Swagger (`/swagger-ui/index.html`).

## 📞 Contato

Se tiver dúvidas ou precisar de ajuda, entre em contato:

- **Autor**: Diego Batista
- **Email**: dev.fogeid@gmail.com
- **GitHub**: [fogeid](https://github.com/fogeid)

---
