# TxView

Transaction analysis web app. Reads from the SQLite database produced by the [txloader](../txloader) ingestion pipeline. Does not write to the database.

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

Set `DB_PATH` to the txloader database before starting any Java service:

```bash
export DB_PATH=/path/to/transactions.db
```

Start order (any order works — services are independent):

```bash
java -jar domain-api/target/domain-api-1.0-SNAPSHOT.jar
java -jar bff/target/bff-1.0-SNAPSHOT.jar
cd web && npm run dev
```

## Key conventions

- **Java**: Spring Boot 3.3, Java 17, Maven multi-module. The three domain modules (`accounts-api`, `transactions-api`, `categories-api`) are plain library JARs; `domain-api` is the single executable that packages them together.
- **Database**: SQLite via `org.xerial:sqlite-jdbc` + `org.hibernate.community.dialect.SQLiteDialect`. Schema is owned by txloader — `ddl-auto=none` everywhere; never add `create` or `update`.
- **BFF clients**: Use Spring `RestClient` (synchronous). Domain API base URLs are configured in `bff/src/main/resources/application.properties` via `txview.api.*` properties.
- **Frontend proxy**: Vite dev server proxies `/api/*` to the BFF at `localhost:8080` — no CORS config needed in dev.
- **Date field**: `transactions.date` is stored as ISO text (`YYYY-MM-DD`) in SQLite; mapped as `String` in JPA entities to avoid dialect conversion issues.

## Database schema (read-only)

```
accounts(id, name, type)
transactions(id, date, merchant, amount, category, account_id, raw_desc)
```
