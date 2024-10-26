# Order Pizza Application


The Order Pizza Application is wrote by Spring Boot and Angular with some basic features:
- Choose product
- Make an order
- View list order
- Change order status

This guide provides step-by-step instructions on how to deploy a **The Order Pizza Application**

## Option 1: Deploy with Docker

### Prerequisites

- Docker client version 27+
- Docker compose version 2+
- Git

### Steps to Deploy

#### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/dai-le-mesoneer/order-pizza.git
cd order-pizza
```
#### 2. Deploy app

Deploy application with Docker compose

```bash
docker compose up -d
```
#### 3. Access the application at local

```bash
http://localhost:4200
```

You can access application with link: [Click me](http://localhost:4200)



## Option 2: Deploy with Spring Boot, Angular

### Prerequisites

- Java version 17
- Maven 3.8+
- Node version 18.*
- npm version 10.*
- PostgreSQL version 14+
- psql client
- Git
- Intellij IDEA Ultimate

#### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/dai-le-mesoneer/order-pizza.git
cd order-pizza
```

#### 2. Create database

```sql
create database "OrderPizza";
\c OrderPizza; // connect to OrderPizza database;
create schema orderpizza;
alter database "OrderPizza" set search_path to orderpizza;
create extension if not exists pgcrypto;
```

#### 3. Build Spring Boot application

```bash
cd order-pizza-api
mvn clean install
```

#### 4. Run Spring Boot Application

Provide enviroment variable (*replace with your credential database*)

```properties
DB_HOST=localhost;DB_NAME=OrderPizza;DB_PASSWORD=123456;DB_PORT=5432;DB_SCHEMA=orderpizza;DB_USER=postgres
```

Run Application with **Intellij IDEA Ultimate**

#### 5. Build Angular Application

```bash
# At project root folder
cd order-pizza-ui
npm install
npm install -g @angular/cli
npm run start
```

#### 6. Access the application at local

```bash
http://localhost:4200
```

You can access application with link: [Click me](http://localhost:4200)
