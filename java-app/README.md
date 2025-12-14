# Shop API

REST API для управления ассортиментом магазина компьютерной техники.
Проект реализован на **Spring Boot** и предоставляет CRUD-операции для различных типов товаров: ноутбуков, мониторов, жёстких дисков и персональных компьютеров.

## Технологический стек

* Java 11+
* Spring Boot
* Spring Web (REST)
* Spring Data JPA
* Hibernate
* PostgreSQL
* Lombok
* OpenAPI / Swagger
* Docker (используется подключение к контейнеру БД)

---

## Архитектура проекта

Проект построен по классической **слоистой архитектуре**:

```
Controller → Service → Repository → Database
```

### 1. Model (Domain Layer)

Базовая сущность:

* `Product` — абстрактный класс, содержащий общие поля:

    * `id`
    * `seriesNumber`
    * `producer`
    * `price`
    * `numberOfProductsInStock`

Наследники `Product`:

* `Laptop`

    * поле: `size` (`LaptopSize`)
* `Monitor`

    * поле: `diagonal`
* `HardDrive`

    * поле: `capacity`
* `PersonalComputer`

    * поле: `formFactor` (`FormFactor`)

Все сущности помечены `@Entity` и сохраняются в БД через JPA.

---

### 2. Repository Layer

Для каждого типа товара используется `CrudRepository`:

* `LaptopRepository`
* `MonitorRepository`
* `HardDriveRepository`
* `PersonalComputerRepository`

Репозитории отвечают за доступ к данным и не содержат бизнес-логики.

---

### 3. Service Layer

Слой сервисов инкапсулирует бизнес-логику и работу с репозиториями:

Интерфейсы:

* `LaptopService`
* `MonitorService`
* `HardDriveService`
* `PersonalComputerService`

Реализации:

* `LaptopServiceImpl`
* `MonitorServiceImpl`
* `HardDriveServiceImpl`
* `PersonalComputerServiceImpl`

Особенности:

* Используется `@Transactional`
* При отсутствии сущности выбрасывается `ResourceNotFoundException`

---

### 4. Controller Layer (REST API)

REST-контроллеры принимают HTTP-запросы и возвращают JSON:

* `LaptopController` → `/api/laptops`
* `MonitorController` → `/api/monitors`
* `HardDriveController` → `/api/hdds`
* `PersonalComputerController` → `/api/pcs`

Контроллеры документированы с помощью **Swagger / OpenAPI** аннотаций.

---

## Запуск проекта

### 1. Предварительные требования

* Docker / Docker Compose
* JDK 11+
* PostgreSQL (или контейнер)

### 2. Конфигурация

Файл `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://db:5432/shop
spring.datasource.username=postgres
spring.datasource.password=shopAPI
spring.jpa.hibernate.ddl-auto=update

server.error.include-stacktrace=never
```

> ⚠️ Хост `db` предполагает использование Docker-сети (например, через docker-compose).

---

### 3. Запуск приложения

```bash
./mvnw spring-boot:run
```

или

```bash
java -jar target/shop-api.jar
```

После запуска приложение будет доступно по адресу:

```
http://localhost:8080
```

---

## API

### Общие принципы

* Формат данных: **JSON**
* Идентификатор сущности передаётся в URL
* Для обновления используется `PUT`
* Для создания используется `POST`

---

### Laptop API (`/api/laptops`)

#### Получить список ноутбуков

```http
GET /api/laptops
```

#### Получить ноутбук по ID

```http
GET /api/laptops/{id}
```

#### Создать ноутбук

```http
POST /api/laptops/add
```

Пример тела запроса:

```json
{
  "seriesNumber": "LAP-123",
  "producer": "Dell",
  "price": 1200.00,
  "numberOfProductsInStock": 10,
  "size": "13 inches"
}
```

#### Обновить ноутбук

```http
PUT /api/laptops/{id}
```

---

### Monitor API (`/api/monitors`)

* `GET /api/monitors`
* `GET /api/monitors/{id}`
* `POST /api/monitors/add`
* `PUT /api/monitors/{id}`

---

### Hard Drive API (`/api/hdds`)

* `GET /api/hdds`
* `GET /api/hdds/{id}`
* `POST /api/hdds/add`
* `PUT /api/hdds/{id}`

---

### Personal Computer API (`/api/pcs`)

* `GET /api/pcs`
* `GET /api/pcs/{id}`
* `POST /api/pcs/add`
* `PUT /api/pcs/{id}`

---

## Swagger / OpenAPI

После запуска проекта Swagger UI доступен по адресу:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Автор

Проект реализован в рамках учебного задания (Docker / Spring Boot practice).