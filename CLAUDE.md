# TxView

Transaction analysis web app. Reads from the PostgreSQL database produced by the [txloader](../txloader) ingestion pipeline. Does not write to the database.

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `accounts-api` | — | Maven library — accounts entity, repository, controller |
| `transactions-api` | — | Maven library — transactions entity, repository, controller |
| `categories-api` | — | Maven library — category aggregation repository, controller |
| `domain-api` | 8082 | Spring Boot — packages the three domain modules into a single deployable |
| `bff` | 8080 | Spring Boot — aggregates domain APIs for the web app |
| `web` | 5173 | React + Vite (TypeScript) — frontend |

## Build

```bash
# Java modules
mvn compile        # compile only
mvn package        # build JARs (only domain-api and bff produce executable fat JARs)

# Frontend
cd web && npm install
cd web && npm run dev      # dev server with BFF proxy
cd web && npm run build    # production build
```

## Running

Point at the txloader Postgres instance before starting any Java service (defaults match `txloader/docker-compose.yaml`, so local dev needs no env vars):

```bash
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=txloader
export DB_USER=myuser
export DB_PASSWORD=mypassword
```

Start order (any order works — services are independent):

```bash
java -jar domain-api/target/domain-api-1.0-SNAPSHOT.jar
java -jar bff/target/bff-1.0-SNAPSHOT.jar
cd web && npm run dev
```

## Key conventions

- **Java**: Spring Boot 3.3, Java 17, Maven multi-module. The three domain modules (`accounts-api`, `transactions-api`, `categories-api`) are plain library JARs; `domain-api` is the single executable that packages them together.
- **Database**: PostgreSQL via `org.postgresql:postgresql` + JDBI3 (`jdbi3-core`, `jdbi3-sqlobject`). Schema is owned by txloader — never issue DDL from txview.
- **BFF clients**: Use Spring `RestClient` (synchronous). Domain API base URLs are configured in `bff/src/main/resources/application.properties` via `txview.api.*` properties.
- **Frontend proxy**: Vite dev server proxies `/api/*` to the BFF at `localhost:8080` — no CORS config needed in dev.
- **Date field**: `transactions.date` is a Postgres `DATE` column; mapped as `String` via JDBI's default column mapper, which the driver formats as ISO `YYYY-MM-DD`.

## Database schema (read-only)

```
accounts(id, name, type)
transactions(id, date, merchant, amount, category, account_id, raw_desc)
```
