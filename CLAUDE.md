# TxView

Transaction analysis web app. Reads from the SQLite database produced by the [txloader](../txloader) ingestion pipeline. Does not write to the database.

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `accounts-api` | 8082 | Spring Boot — accounts table |
| `transactions-api` | 8083 | Spring Boot — transactions table |
| `categories-api` | 8084 | Spring Boot — spend aggregated by category |
| `bff` | 8080 | Spring Boot — aggregates domain APIs for the web app |
| `web` | 5173 | React + Vite (TypeScript) — frontend |

## Build

```bash
# Java modules
mvn compile        # compile only
mvn package        # build fat JARs

# Frontend
cd web && npm install
cd web && npm run dev      # dev server with BFF proxy
cd web && npm run build    # production build
```

## Running

Set `DB_PATH` to the txloader database before starting any Java service:

```bash
export DB_PATH=/path/to/transactions.db
```

Start order (any order works — services are independent):

```bash
java -jar accounts-api/target/accounts-api-1.0-SNAPSHOT.jar
java -jar transactions-api/target/transactions-api-1.0-SNAPSHOT.jar
java -jar categories-api/target/categories-api-1.0-SNAPSHOT.jar
java -jar bff/target/bff-1.0-SNAPSHOT.jar
cd web && npm run dev
```

## Key conventions

- **Java**: Spring Boot 3.3, Java 17, Maven multi-module. Each domain API is a standalone Spring Boot app with its own `application.properties`.
- **Database**: SQLite via `org.xerial:sqlite-jdbc` + `org.hibernate.community.dialect.SQLiteDialect`. Schema is owned by txloader — `ddl-auto=none` everywhere; never add `create` or `update`.
- **BFF clients**: Use Spring `RestClient` (synchronous). Domain API base URLs are configured in `bff/src/main/resources/application.properties` via `txview.api.*` properties.
- **Frontend proxy**: Vite dev server proxies `/api/*` to the BFF at `localhost:8080` — no CORS config needed in dev.
- **Date field**: `transactions.date` is stored as ISO text (`YYYY-MM-DD`) in SQLite; mapped as `String` in JPA entities to avoid dialect conversion issues.

## Database schema (read-only)

```
accounts(id, name, type)
transactions(id, date, merchant, amount, category, account_id, raw_desc)
```
